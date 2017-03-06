/**
 * Created by RoK.
 * All rights reserved =)
 */

import com.rok.xml.ejb.XmlConfigEditorLocal;
import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

public class EjbTester extends TestCase {

    private Context context;

    protected void setUp() throws Exception {
        context = EJBContainer.createEJBContainer().getContext();
    }

    public void testBean() throws Exception {

        XmlConfigEditorLocal bean = (XmlConfigEditorLocal) context.lookup("java:global/XmlConfigEditorEjb/XmlConfigEditorEJB");

        assertNotNull(bean);
        System.out.println(bean.getConfigModificationInfo());
    }

//    public void testBlue() throws Exception {
//
//        Friend blue = (Friend) context.lookup("java:global/lookup-of-ejbs/BlueBean");
//
//        assertNotNull(blue);
//        assertEquals("Blue says, Hello!", blue.sayHello());
//        assertEquals("My friend Red says, Hello!", blue.helloFromFriend());
//    }
}