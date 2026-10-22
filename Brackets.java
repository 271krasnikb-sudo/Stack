public class Brackets
{
    public static void main(String[] args)
    {
        System.out.println("Should be true");
        System.out.println(checkBrackets(" "));
        System.out.println(checkBrackets("{}"));
        System.out.println(checkBrackets("{}{}"));
        System.out.println(checkBrackets("{{}}"));
        System.out.println(checkBrackets("{{{}{{}}}}"));

        System.out.println("\nShould be false");
        System.out.println(checkBrackets("{"));
        System.out.println(checkBrackets("}{"));
        System.out.println(checkBrackets("{}}{"));
        System.out.println(checkBrackets("{{}"));
        System.out.println(checkBrackets("{{}}}{}"));
    }
    
    public static boolean checkBrackets(String b)
    {
        ArrayStack<Character> brackets = new ArrayStack<>();
        for(int i = 0; i<b.length();i++){
            char c = b.charAt(i);
            if(c =='{'){
                brackets.push(c);
            } else if(c=='}'){
                if(brackets.size()==0){
                    return false;
                }else{
                    brackets.pop();
                }
            }

        }
        return (brackets.size()==0);

       
    }
}

