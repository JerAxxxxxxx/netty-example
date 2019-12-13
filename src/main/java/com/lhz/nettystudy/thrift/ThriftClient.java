package com.lhz.nettystudy.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/13 9:00
 */
public class ThriftClient {
    public static void main(String[] args) throws TException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getAge());
            System.out.println(person.getName());
            System.out.println(person.isMarried());

            Person person2 = new Person();
            person2.setAge(34);
            person2.setMarried(true);
            person2.setName("李四");

            client.savePerson(person2);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            transport.close();
        }
    }
}
