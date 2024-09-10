package wanted.pre_onboarding;

/*
    Class 관련 메타 정보 -> Method Area에 저장됨
    메서드의 바이트 코드 자체 -> Method Area에 저장됨
 */
public class JVMExample {
    // Method Area에 저장됨
    private static int staticVar = 10; // static 변수

    // Heap에 저장됨
    private int instanceVar; // 인스턴스 변수

    public JVMExample(int instanceVar) {
        // Stack에 저장됨
        this.instanceVar = instanceVar;
    }

    public int sum(int a, int b) {
        // a와 b는 Stack에 저장됨
        int result = a + b; // result도 Stack에 저장됨
        return result;
    }

    public static void main(String[] args) {
        // args는 Stack에 저장됨
        JVMExample example = new JVMExample(5); // example 참조(주소)는 Stack에 저장, 객체는 Heap에 저장
        int sum = example.sum(3, 4); // sum 변수는 Stack에 저장
        System.out.println("Sum: " + sum);
    }
}
