/**
 * Created by RoK.
 * All rights reserved =)
 */

import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.ejb.XmlConfigEditorLocal;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import static org.junit.Assert.assertNotNull;

public class EjbTester {

    private XmlConfigEditorLocal bean;
    private ConfigModificationInfo configModificationInfo;

    @BeforeClass
    protected void setUp() throws Exception {
        Context context = EJBContainer.createEJBContainer().getContext();
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
    protected void tearDown() throws Exception {
        if (bean != null) {
            bean.cancelConfigEditing(configModificationInfo);
        }
    }
}