package org.etherpad_lite_client;

import java.util.List;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EPLiteClientTest extends TestCase {
	private EPLiteClient client;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EPLiteClientTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( EPLiteClientTest.class );
    }

    /**
     * Rigourous Testing
     */
    public void testEPLiteClient() {
		this.client = new EPLiteClient("http://fastreboot.de:9001", "8EkKqoT0CR28PcRDpF311XLtspAchXuM");
        assertTrue( this.testPadContents() );
        assertTrue( this.testListAllPads() );
    }

    private boolean testPadContents() {
        client.createPad("java_test_pad");
        client.setText("java_test_pad", "foo!!!");
        HashMap pad = client.getText("java_test_pad");
        client.deletePad("java_test_pad");

        String text = pad.get("text").toString();
        return text.equals("foo!!!\n");
    }

    private boolean testListAllPads() {
        client.createPad("java_test_pad_1");
        client.createPad("java_test_pad_2");
        HashMap result = client.listAllPads();
        client.deletePad("java_test_pad_1");
        client.deletePad("java_test_pad_2");

        List padIDs = (List) result.get("padIDs");
        for (int i = 0; i < padIDs.size()-1; i++) {
			if(padIDs.get(i).equals("java_test_pad_1")){
				return true;
			}
		}
        return false;
    }
}
