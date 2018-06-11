/**
 * @CopyRight DeckITPL
 */
package com.sysintelli.ims.repository;

import com.sysintelli.ims.entity.FbOperationMstr;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author deck
 */
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<FbOperationMstr, Long> {

}
