### Security

1) Security Filter Chain
   - Request -> Auth Filter -> UserDetailsService -> SecurityContext -> DispatchServlet
   - When the user accesses an api then the first thing that is called, is the SecurityFilterChain
   - The SpringBootWebSecurityConfiguration has a defaultSecurityFilterChain
that requires all requests to be authenticated & sets the default login page. httpBasic() defines
the Authentication filter.
     - The Auth filter will ask the UserDetailsService for user information
     - The auth filter then updates the SecurityContextHolder & then the request is getting forwarded to the
dispatch servlet