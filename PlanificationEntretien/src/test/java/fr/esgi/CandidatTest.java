package fr.esgi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

public class CandidatTest extends TestCase {

    public CandidatTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( CandidatTest.class ); }

    private void init(){
    }

    public void test_should_find_by_id()
    {
        init();
        Assert.assertNotNull("1");
    }
}
