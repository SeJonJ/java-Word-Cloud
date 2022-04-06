package HJproject.Hellospring.Practice;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import java.net.URISyntaxException;
import java.net.URL;

public class JarTest {
    JarTest(){
        URL in = getClass().getResource(this.getClass().getName());
        System.out.println(in);


    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        new JarTest();
        System.out.println(JarTest.class.getPackage());
        File path = new File("");

        System.out.println(path.getCanonicalPath()+"/src/main/resources/DynamicMusic/New_DynamicMusic-3.0-SNAPSHOT.jar");

    }

}
