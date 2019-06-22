package designpattern.mix;

public class LS extends AbstractLS {
    //最简单的ls命令
    protected String echo(CommandVO vo) {
        return FileManager.ls(vo.getCommandName());
    }

    //参数为空
    protected String getOperateParam() {
        return super.DEFAULT_PARAM;
    }
}

