// test for https://github.com/kelloggm/object-construction-checker/issues/326

import org.checkerframework.checker.calledmethods.qual.*;
import org.checkerframework.checker.mustcall.qual.*;
import org.checkerframework.common.returnsreceiver.qual.*;

import java.io.InputStream;

class ManualMustCallEmptyOnConstructor {

    // Test that writing @MustCall({}) on a constructor results in an error
    @InheritableMustCall("a")
    static class Foo {
        final @Owning InputStream is;

        // :: error: inconsistent.constructor.type
        @MustCall({}) Foo(@Owning InputStream is) {
            this.is = is;
        }

        @EnsuresCalledMethods(value = "this.is", methods = "close")
        void a() throws Exception {
            is.close();
        }
    }
}
