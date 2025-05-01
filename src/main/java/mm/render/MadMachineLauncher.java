package mm.render;

public class MadMachineLauncher extends SimpleFXRenderEngine {

    public static void main(String[] args) {
        MadMachineLauncher.launch(args);
    }

    @Override
    protected RenderLoop createRenderLoop() {
        return new CrazyMachines();
    }

    @Override
    protected String getProgramName() {
        return "Mad machines";
    }

    @Override
    protected int getInitialWidth() {
        return 1920;
    }

    @Override
    protected int getInitialHeight() {
        return 1080;
    }

}
