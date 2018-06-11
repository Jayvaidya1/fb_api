/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.proxy;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Jay
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class FbOperationProxy implements Serializable {

    private Long id;
    private String ytChannel;
    private String fbRefId;
    private Long lastAccess;

    /**
     * @Additiona Fields
     */
    private List<PostProxy> posts;

    /**
     * @Constructor FbOperationProxy_01
     */
    public FbOperationProxy(Long id) {
        this.id = id;
    }

    /**
     * @Constructor FbOperationProxy_02
     */
    public FbOperationProxy(Long id, String ytChannel, String fbRefId, Long lastAccess) {
        this.id = id;
        this.ytChannel = ytChannel;
        this.fbRefId = fbRefId;
        this.lastAccess = lastAccess;
    }
}
