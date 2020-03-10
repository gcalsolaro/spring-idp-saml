
# Spring Idp SAML

![Java CI with Maven](https://github.com/gcalsolaro/spring-idp-saml/workflows/Java%20CI%20with%20Maven/badge.svg)
> **Sample application using SAML for Authentication powered by Spring Security**


## Table of Contents

   * [Spring Idp Saml](#spring-idp-saml)
      * [Architecture](#architecture)
      * [Prerequisites](#prerequisites)
      * [Running Instructions](#running-instructions)
      

## Architecture

The technology stack used is provided by Spring, in particular:

* **_Spring Core_** - 5.1.5.RELEASE
* **_Spring Security_** - 5.1.4.RELEASE
* **_Spring Security SAML_** - 1.0.3.RELEASE

## Prerequisites
* **_JDK 8_** - Install JDK 1.8 version
* **_Maven_** - Download latest version
* **_Oracle Weblogic_** - 12.x version

## Running Instructions

 - Go to http://www.ssocircle.com/ and create new account
 - Go to "**My Client Certificate**" and click on "**Download the SSOCircle CA Certificate**"
 - Create a keystore file (.jks) and put it inside the project (**idp-saml-web/src -> main -> resources -> saml**) 
 - Go to WebSecurityConfig.java and:
	  1) Modify properly the public method **KeyManager keyManager()**
	  2) Modify properly the public method public **MetadataGenerator metadataGenerator()** and create random app name
 - Start the server and point to http://localhost:7001/saml/metadata/
 - Copy the content inside your downloaded file
 - Go back to http://www.ssocircle.com/ and paste it inside section "**Insert the SAML Metadata information of your SP**":
	 1) check value in section **Attributes sent in assertion** that you need
	 2) insert "http://localhost:7001" as FQDN and click **submit**
 - Go to http://localhost:7001/saml/login?idp=https://idp.ssocircle.com for login
