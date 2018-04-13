package mtopsdk.mtop.domain;

public enum EnvModeEnum {
    ONLINE(0),
    PREPARE(1),
    TEST(2),
    TEST_SANDBOX(3);
    
    private int envMode;

    private EnvModeEnum(int i) {
        this.envMode = i;
    }

    public final int getEnvMode() {
        return this.envMode;
    }
}
