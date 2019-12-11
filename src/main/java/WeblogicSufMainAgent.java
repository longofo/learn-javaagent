import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class WeblogicSufMainAgent {
    static {
        System.out.println("SufMainAgent static block run...");
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("SufMainAgent agentArgs: " + agentArgs);

        Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> cls : classes) {
            System.out.println("SufMainAgent get loaded class: " + cls.getName());
        }

        instrumentation.addTransformer(new DefineTransformer(), true);
    }

    static class DefineTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("SufMainAgent transform Class:" + className);
            return classfileBuffer;
        }
    }
}
