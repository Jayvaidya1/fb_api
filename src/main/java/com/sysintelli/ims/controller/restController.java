/**
 * @CopyRight DeckITPL
 */
package com.sysintelli.ims.controller;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.sysintelli.ims.config.ConfigBean;
import com.sysintelli.ims.config.FBGraphAPI;
import com.sysintelli.ims.proxy.ConfigurationProxy;
import com.sysintelli.ims.proxy.FbOperationProxy;
import com.sysintelli.ims.repository.Repository;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author deck
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:4200", "http://202.65.142.18:80"})
@RequestMapping("/fb")
@Transactional
public class restController {

    private Repository repo;
    public static final Logger logger = Logger.getLogger(restController.class.getName());

    @Autowired
    public restController(Repository repo) {
        this.repo = repo;
    }

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public FBGraphAPI fbGraphApi;
    @Autowired
    public ConfigBean configBean;

    @GetMapping("/addChannel/{ytChannelName}")
    public String addChannel(HttpSession httpSession, @PathVariable("ytChannelName") String fbChannelName) {
        ConfigurationProxy config = configBean.getActiveConfiguration();
        if (config == null) {
            logger.info("There are no any active access token available in DB");
            return "There are no any active access token available in DB";
        }
        FacebookClient fbClient = new DefaultFacebookClient(config.getAccessToken());
        String fbId = fbGraphApi.isChannelAvailable(fbChannelName, fbClient);
        if (fbId == null) {
            logger.info("There are no channel name of " + fbChannelName);
            return "There are no channel name of " + fbChannelName;
        }
        FbOperationProxy proxy = new FbOperationProxy();
        proxy.setYtChannel(fbChannelName);
        proxy.setFbRefId(fbId);
        boolean flag = fbGraphApi.addChannelData(proxy, fbClient);
        if (flag) {
            return "Channel Data successfully Added.";
        }
        return "Channel Data already available in DB.";
    }

    @GetMapping("/runSchedular/{limit}")
    public String runSchedular(HttpSession httpSession, @PathVariable("limit") int limit) {
        ConfigurationProxy config = configBean.getActiveConfiguration();
        if (config == null) {
            logger.info("There are no any active access token available in DB");
            return "There are no any active access token available in DB";
        }
        FacebookClient fbClient = new DefaultFacebookClient(config.getAccessToken());
        List<FbOperationProxy> list = fbGraphApi.getFbOperationList(limit);
        for (FbOperationProxy fbOperation : list) {
            fbGraphApi.addChannelData(fbOperation, fbClient);
        }

        return "Channel Data already available in DB.";
    }

}
