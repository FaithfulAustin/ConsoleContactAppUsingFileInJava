

public class main {
    public static void main(String[] args) {
        contactImp run = new contactImp();
        run.showMenu();


        // abc = 3 yep               3     1    0
        //abcc = 4 yep  #            3     1    0

        //abccc = 5 nope             2     1    3
        //aabbcd = no                4     1    0
        // aabbccddeefghi = no       9     1

        // abcdefghhgfedecba = yep # 1     1
        // System.out.println(validStr("aabbccddeefghi"));
    }

    }

//    public static String validStr(String str){
//        int count;
//        int lengthOfStr = str.length();
//        String yesOrNo = "";
//
//        char strArr[] = str.toCharArray();
//
//        for(int i = 0; i<strArr.length;i++){
//            count=1;
//        for (int j = i+1;j<strArr.length;j++){
//            if(strArr[i]==strArr[j]){
//                count++;
//                strArr[j]='0';
//
//            }
//
//        }
////           System.out.println(count+" "+strArr[i]);
//
//
//
//        if(lengthOfStr%count==0 && strArr[i]!='0'){
//
////            System.out.println(strArr[i]);
//            yesOrNo+="yes";
//
//        }
//        else if(lengthOfStr%count!=0 && strArr[i]!='0'){
//            yesOrNo+="no"+"@";
//            }
//        }
//        //System.out.println(yesOrNo);
//        String yesStr = "";
//       String[] yNSplit =yesOrNo.split("yes");
//        System.out.println(yNSplit.length);
////        System.out.println(" ");
////        for (int i =0; i< yNSplit.length;i++ ){
//////            System.out.println(yNSplit[i]);
////            yesStr+=yNSplit[i];
////
////        }
////        String[] yesArr =  yesStr.split("@");
////        System.out.println(yesArr.length);
////
////        System.out.println(" ");
////        System.out.println(" ");
////
////        System.out.println(yNSplit.length);
//
//
//        return "";
//    }
//}
