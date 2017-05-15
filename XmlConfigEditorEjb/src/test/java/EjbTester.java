/*
 * Created by RoK.
 * All rights reserved =)
 */

import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.ejb.XmlConfigEditorLocal;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import static org.junit.Assert.assertNotNull;

public class EjbTester {

    private static XmlConfigEditorLocal bean;
    private static ConfigModificationInfo configModificationInfo;
    private static Context context;

    @BeforeClass
    public static void setUp() throws Exception {
        context = EJBContainer.createEJBContainer().getContext();
        bean = (XmlConfigEditorLocal) context.lookup("java:global/XmlConfigEditorEjb/XmlConfigEditorEJB");
    }

    @Test
    public void testBean() throws Exception {
        assertNotNull(bean);
        configModificationInfo = bean.getConfigModificationInfo();
        assertNotNull(configModificationInfo);
        System.out.println(configModificationInfo);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (bean != null) {
            bean.cancelConfigEditing(configModificationInfo);
        }
        if (context != null) {
            context.close();
        }
    }
}