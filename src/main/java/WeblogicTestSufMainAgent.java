import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class WeblogicTestSufMainAgent {
    public static void main(String[] args) throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
        //获取当前系统中所有 运行中的 虚拟机
        System.out.println("TestSufMainAgent start...");
        String option = args[0];
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        if (option.equals("list")) {
            for (VirtualMachineDescriptor vmd : list) {
                //如果虚拟机的名称为 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
                //然后加载 agent.jar 发送给该虚拟机
                System.out.println(vmd.displayName());
            }
        } else if (option.equals("attach")) {
            String jProcessName = args[1];
            String agentPath = args[2];
            for (VirtualMachineDescriptor vmd : list) {
                if (vmd.displayName().equals(jProcessName)) {
                    VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                    virtualMachine.loadAgent(agentPath);
                }
            }
        }
    }
}
