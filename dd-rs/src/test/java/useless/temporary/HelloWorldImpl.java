package useless.temporary;

import javax.jws.WebMethod;

public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHi(String who) {
        return "Hi, " + who;
    }

    @Override
    @WebMethod(exclude = true)
    public String sayHello(String who) {
        return "Hello, " + who;
    }

}