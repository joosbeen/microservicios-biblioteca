package com.servicio.oauth2.server.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.servicio.oauth2.server.entity.Usuario;
import com.servicio.oauth2.server.service.UsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioService iUsuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Map<String, Object> info = new HashMap<String, Object>();
		
		Usuario usuario = iUsuarioService.findByUsername(authentication.getName());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
