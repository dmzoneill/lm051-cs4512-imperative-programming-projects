
public class Exam2
{
  public Exam2()
  {
     System.out.println("Be the machine!!!");
  }
  public Exam2(int inValue)
  {
    new Test1();
    System.out.println("The Answer is " + inValue);
  }
  public static void main(String args[])
  {
     Test1 temp = new Test1();
     temp.start(34);
  }
  public void start()
  {
    System.out.println("Should keep up with the reading");
  }
  public static void main()
  {
    System.out.println("Wished I did that exercise that covered this");
  }
}
class Test1 extends Exam2
{
  public int Test1()
  {
    System.out.println("I will study more next time!!");
    return 0;
  }
  public void start(int inValue)
  {
    System.out.println("I am sure i know this!");
  }
}

