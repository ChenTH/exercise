package designpattern.mix;

public class LS_L extends AbstractLS {
    protected String echo(CommandVO vo) {
        return FileManager.ls_l(vo.getCommandName());
    }

    //l选项
    protected String getOperateParam() {
        return super.L_PARAM;
    }
}


