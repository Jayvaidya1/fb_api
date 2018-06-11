/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.proxy;

import java.io.Serializable;
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
public class ConfigurationProxy implements Serializable {

    private Long id;
    private String accessToken;
    private Boolean isActive;
    private String details;

    /**
     * @Constructor ConfigurationProxy_01
     */
    public ConfigurationProxy(Long id) {
        this.id = id;
    }

    /**
     * @Constructor ConfigurationProxy_02
     */
    public ConfigurationProxy(Long id, String accessToken, Boolean isActive, String details) {
        this.id = id;
        this.accessToken = accessToken;
        this.isActive = isActive;
        this.details = details;
    }
}
