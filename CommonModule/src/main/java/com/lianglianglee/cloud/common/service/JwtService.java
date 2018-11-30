package com.lianglianglee.cloud.common.service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lianglianglee.cloud.common.config.RsaKeyConfig;
import com.lianglianglee.cloud.common.exception.AuthorizationException;
import com.lianglianglee.cloud.common.model.EditJwtModel;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 李亮亮
 */
public class JwtService {
  public static final String EXPECTED_ISSUER = "edit-Server";
  public static final String AUDIENCER = "edit-Client";

  private ObjectMapper objectMapper = new ObjectMapper();

  private final Logger logger = LoggerFactory.getLogger(RsaKeyConfig.class);

  private RsaJsonWebKey rsaJsonWebKey = new RsaKeyConfig().getRsaJsonWebKey();

  /**
   * 生成token.
   */
  public String generateToken(EditJwtModel model, int timeOfminutes) {
    String ret = null;
    try {
      ret = prepareToken(model, timeOfminutes);
    } catch (JoseException e) {

      logger.error(e.getMessage());
    } catch (JsonProcessingException ex) {
      logger.error(ex.getMessage());
    }
    return ret;
  }

  private String prepareToken(EditJwtModel model, int timeOfminutes)
    throws JoseException, JsonProcessingException {

    logger.info("##GENETATE TOKEN## with userName " + model.getUserName());

    JwtClaims claims = new JwtClaims();
    claims.setIssuer(EXPECTED_ISSUER);
    claims.setAudience(AUDIENCER);
    // make the interval big enough for testing.
    claims.setExpirationTimeMinutesInTheFuture(timeOfminutes);
    claims.setGeneratedJwtId();
    claims.setIssuedAtToNow();
    claims.setNotBeforeMinutesInThePast(2);
    claims.setClaim("User", objectMapper.writeValueAsString(model));

    JsonWebSignature jws = new JsonWebSignature();

    jws.setPayload(claims.toJson());

    jws.setKey(rsaJsonWebKey.getPrivateKey());

    jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

    String jwt = jws.getCompactSerialization();

    return jwt;
  }

  public EditJwtModel getAuthenticatedUser(String jwt) {
    EditJwtModel model = getMassJwtModel(jwt);
    return model;
  }


  private EditJwtModel getMassJwtModel(String jwt) {
    EditJwtModel model = null;
    JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
      .setAllowedClockSkewInSeconds(30).setExpectedIssuer(EXPECTED_ISSUER)
      .setExpectedAudience(AUDIENCER).setVerificationKey(rsaJsonWebKey.getKey()).build();
    try {
      JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
      String s = (String) jwtClaims.getClaimValue("User");
      model = objectMapper.readValue(s, EditJwtModel.class);
    } catch (InvalidJwtException e) {
      logger.error(e.getMessage());
    } catch (JsonParseException e) {
      logger.error(e.getMessage());
    } catch (JsonMappingException e) {
      logger.error(e.getMessage());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return model;
  }

  /**
   * 校验.
   */
  public EditJwtModel customizedValidation(String jwt) {

    EditJwtModel massJwtModel = getMassJwtModel(jwt);
    if (massJwtModel == null) {
      logger.error("not found the authorized object or the authorization has been expiration!");
      throw new AuthorizationException("Token验证失败");
    }
    return massJwtModel;

  }

}
