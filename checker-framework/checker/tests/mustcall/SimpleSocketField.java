// a test that sockets in fields are considered @MustCall("close")

import org.checkerframework.checker.mustcall.qual.MustCall;

import java.net.Socket;

class SimpleSocketField {
    Socket mySock = new Socket();

    SimpleSocketField() throws Exception {
        @MustCall("close") Socket s = mySock;
        // This assignment is safe, because the only possible value of mySock here is the
        // unconnected socket in the field initializer.
        @MustCall({}) Socket s1 = mySock;
    }

    void test() {
        @MustCall("close") Socket s = mySock;
        // :: error: assignment.type.incompatible
        @MustCall({}) Socket s1 = mySock;
    }
}
