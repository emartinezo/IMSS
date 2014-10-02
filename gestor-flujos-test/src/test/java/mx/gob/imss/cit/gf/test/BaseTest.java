package mx.gob.imss.cit.gf.test;

import java.io.File;

import mx.gob.imss.cit.gf.test.service.SesionServiceTest;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calse base para la s pruebas unitarias
 * @author admin
 *
 */

@ArquillianSuiteDeployment
@RunWith(Arquillian.class)
public  class  BaseTest {
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SesionServiceTest.class);
	
	/**
	 * constante publica de deploy
	 */
	public static final String DEPLOY_GLASSFISH = "glassfish";

	/**
	 * Metodo que se realizara el deploy e inicialara y se ejecutara antes de las pruebas
	 * unitarias
	 * @return
	 */
	@Deployment(testable = true,name=DEPLOY_GLASSFISH)
	public static EnterpriseArchive createDeployment() {
		JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "ejb-test.jar")
                .addPackages(true,"mx.gob.imss.cit.gf");
		
	File appEarXml=new File("src/test/resources/application.xml");
	
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "gestor-flujos-test.ear")        		
                                         .setApplicationXML(appEarXml)
                                         .addAsModule(ejb);

        return ear;

    
	}
	
	/**
	 * Prueba
	 */
	@Test
	public void initialiationError(){
		LOG.debug("dummy");
	}
	
	
	

}

