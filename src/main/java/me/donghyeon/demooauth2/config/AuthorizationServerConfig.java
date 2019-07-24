package me.donghyeon.demooauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

// 인증서버의 역할은 토큰발급 토큰 리프래쉬 등.
// OAuth2 로 인증을 거치는 과정을 이 클래스가 처리함.
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    //발급받은 토큰들을 저장하는 저장소
    //기본적으로 InMemory?
    private TokenStore tokenStore;

    @Autowired
    //시큐리티의 가장 중요한 역할, 인증할때 많이 씀
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        //AuthenticationManager 클래스가 아래내용을 보고 인증을 처리한다.
        configurer
                .inMemory()
                .withClient("donghyeon-client")
                .secret(passwordEncoder.encode("donghyeon-password"))
                .authorizedGrantTypes("password",
                        "authorization_code",
                        "refresh_token",
                        "implicit")
                .scopes("read","write","trust")
                .accessTokenValiditySeconds(1*60*60)
                .refreshTokenValiditySeconds(6*60*60);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }
}

