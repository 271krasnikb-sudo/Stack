import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class StackTest<T> {
   private static boolean failed = false;
   private static boolean arrayFailed;
   private static boolean linkedFailed;
   private static ArrayList<String> methodNames = new ArrayList();
   private static StackInterface<Integer> stack;
   private static String message;
   private static String methodName;
   private static Class<?> c;
   private static boolean display = true;

   public StackTest() {
   }

   public static void main(String[] args) {
      testInterface();
      testStack("ArrayStack");
      arrayFailed = failed;
      failed = false;
      testStack("LinkedStack");
      linkedFailed = failed;
      if (!arrayFailed && !linkedFailed) {
         System.out.println("Fantabulous!  You have completed the Stack assignment");
      } else {
         System.out.println("Bummer, you have not finished the Stack assignment.  Try again.");
      }

   }

   private static void testStack(String type) {
      stackTest(type);
      if (failed) {
         System.out.println(type + " class does not pass the test\n");
      }

   }

   private static void testInterface() {
      try {
         c = Class.forName("StackInterface");
         Member[] methods = c.getMethods();

         for(Member method : methods) {
            methodNames.add(((Method)method).toGenericString());
         }
      } catch (ClassNotFoundException var5) {
         failure("Epic Failure: missing interface StackInterface");
         return;
      }

      methodName = "public abstract void StackInterface.clear()";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract int StackInterface.size()";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract T StackInterface.pop() throws StackUnderflowException";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract T StackInterface.push(T)";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract T StackInterface.peek() throws StackUnderflowException";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract boolean StackInterface.empty()";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      methodName = "public abstract int StackInterface.search(java.lang.Object)";
      if (!methodNames.contains(methodName)) {
         failure("Missing interface method: " + methodName);
      }

      if (failed) {
         System.out.println("\nCorrupted interface StackInterface");
      }

   }

   private static void stackTest(String type) {
      System.out.println("Now testing your " + type + " class:");

      try {
         if (type.equals("ArrayStack")) {
            stack = new ArrayStack();
         } else {
            stack = new LinkedStack();
         }

         c = Class.forName(type);
      } catch (ClassNotFoundException var12) {
         failure("Epic Failure: missing " + type + " class");
         return;
      } catch (NoClassDefFoundError var13) {
         failure("Epic Failure: missing " + type + " class");
         return;
      } catch (NoSuchMethodError var14) {
         failure("Failed: missing constructor " + type + "()");
         return;
      }

      if (!(stack instanceof StackInterface)) {
         failure("Failed: " + type + " does not implement StackInterface");
      } else {
         display(type + " implements StackInterface test");
         if (stack.size() != 0) {
            failure("Failed: incorrect size value");
         } else if (!stack.empty()) {
            failure("Failed: incorrect empty method");
         } else {
            if ((Integer)stack.push(new Integer(1)) != 1) {
               failure("push(element) method is not correct");
            }

            if (stack.size() != 1) {
               failure("Failed: incorrect size value");
            } else if (stack.empty()) {
               failure("Failed: incorrect empty method");
            } else {
               if ((Integer)stack.peek() != 1) {
                  failure("peek method is not correct");
               }

               if ((Integer)stack.pop() != 1) {
                  failure("pop method is not correct");
               }

               if (stack.size() != 0) {
                  failure("Failed: incorrect size value");
               } else if (!stack.empty()) {
                  failure("Failed: incorrect empty method");
               } else {
                  try {
                     stack.pop();
                     failure("pop method does not correctly throw StackUnderflowException");
                  } catch (StackUnderflowException var10) {
                     display("pop method correctly throws StackUnderflowException");
                  } catch (ArrayIndexOutOfBoundsException var11) {
                     failure("pop method does not correctly throw StackUnderflowException");
                  }

                  try {
                     stack.peek();
                     failure("peek method does not correctly throw StackUnderflowException");
                  } catch (StackUnderflowException var8) {
                     display("peek method correctly throws StackUnderflowException");
                  } catch (ArrayIndexOutOfBoundsException var9) {
                     failure("peek method does not correctly throw StackUnderflowException");
                  }

                  for(int i = 10; i <= 1000; i += 10) {
                     try {
                        stack.push(new Integer(i));
                     } catch (ArrayIndexOutOfBoundsException var15) {
                        failure("array does not dynamically expand");
                        break;
                     }
                  }

                  if (stack.size() != 100) {
                     failure("Failed: incorrect size value");
                  } else if (stack.empty()) {
                     failure("Failed: incorrect empty method");
                  } else {
                     int x = (Integer)stack.peek();
                     if (x != 1000) {
                        failure("push and/or peek methods are incorrect");
                     }

                     while(x > 10) {
                        if (x != (Integer)stack.pop()) {
                           failure("push and/or pop methods are incorrect");
                        }

                        x -= 10;
                        if (x != (Integer)stack.peek()) {
                           failure("push and/or peek methods are incorrect");
                        }
                     }

                     if (stack.size() != 1) {
                        failure("Failed: incorrect size value");
                     } else if (stack.empty()) {
                        failure("Failed: incorrect empty method");
                     } else {
                        if ((Integer)stack.peek() != 10) {
                           failure("peek method is not correct");
                        }

                        if ((Integer)stack.pop() != 10) {
                           failure("pop method is not correct");
                        }

                        if (stack.size() != 0) {
                           failure("Failed: incorrect size value");
                        } else if (!stack.empty()) {
                           failure("Failed: incorrect empty method");
                        } else {
                           try {
                              stack.pop();
                              failure("pop method does not correctly throw StackUnderflowException");
                           } catch (StackUnderflowException var6) {
                              display("pop method again correctly throws StackUnderflowException");
                           } catch (ArrayIndexOutOfBoundsException var7) {
                              failure("pop method does not correctly throw StackUnderflowException");
                           }
                           try {
                              stack.peek();
                              failure("peek method does not correctly throw StackUnderflowException");
                           } catch (StackUnderflowException var4) {
                              display("peek method again correctly throws StackUnderflowException");
                           } catch (ArrayIndexOutOfBoundsException var5) {
                              failure("peek method does not correctly throw StackUnderflowException");
                           }

                           if (!failed) {
                              display("size method is correct");
                              display("empty method is correct");
                              display("push method is correct");
                              display("peek method is correct");
                              display("pop method is correct");
                           }

                           for(int i = 10; i > 0; --i) {
                              stack.push(new Integer(i));
                           }

                           if (stack.search(new Integer(3)) != 3) {
                              failure("search method is incorrect");
                           }

                           if (stack.search(new Integer(8)) != 8) {
                              failure("search method is incorrect");
                           }

                           if (stack.search(new Integer(1)) != 1) {
                              failure("search method is incorrect");
                           }

                           if (stack.search(new Integer(10)) != 10) {
                              failure("search method is incorrect");
                           }

                           if (stack.search(new Integer(12)) != -1) {
                              failure("search method is incorrect");
                           }

                           for(int i = 1; i <= 10; ++i) {
                              if ((Integer)stack.pop() != i) {
                                 failure("search method did not restore stack to original state");
                                 break;
                              }
                           }

                           if (!failed) {
                              display("search method is correct");
                           }

                           for(int i = 0; i < 500; ++i) {
                              stack.push(new Integer(i));
                           }

                           stack.clear();
                           if (stack.size() != 0) {
                              failure("Failed: incorrect size value");
                           } else if (!stack.empty()) {
                              failure("Failed: incorrect empty method");
                           } else {
                              try {
                                 if (stack.pop() != null) {
                                    failure("clear method is incorrect");
                                 }
                              } catch (StackUnderflowException var3) {
                              }

                              if (!failed) {
                                 display("clear method is correct");
                              }

                              if (!failed) {
                                 System.out.println("Congratulations!  Your " + type + " class is correct\n");
                              }

                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static void failure(String str) {
      if (display) {
         System.out.println("*** " + str);
      }

      failed = true;
   }

   private static void display(String str) {
      if (display) {
         System.out.println("Passed " + str);
      }

   }
}