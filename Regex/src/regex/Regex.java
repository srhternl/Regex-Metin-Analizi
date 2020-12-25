/**
*
* @author Serhat ERENEL b171210084@sakarya.edu.tr
* @since 6.03.2020
* <p>
* Text dosyasının içindeki sesli harfi, kelime sayısını, cümle sayısını,
* mail sayısını, web sitesi sayısını bulan program.
* </p>
*/

package regex;
import java.io.*;//Dosya okuma işlemlerinin hepsini kapsayan sınıf.
import java.util.regex.Matcher;//Regex işlemi için kullandığım sınıf.
import java.util.regex.Pattern;//Regex işlemi için kullandığım sınıf.





public class Regex 
{ 
    public static void regexHesaplama()
    {
       // Değişkenleri tanımladığım kısım.
       String metin ;//Text içindeki metin için tanımladığım değişken.
       String []veri = new String[1059999999]; //Bütün satırları okuması için tanımladığım dizi.
       int sesliHarf=0;//Sesli harf saysını tutmak için tanımladığım sayaç.
       int kelime=0; //Kelime saysını tutmak için tanımladığım sayaç.
       int cumle=0; //Cümle saysını tutmak için tanımladığım sayaç.
       int mail=0; //Mail saysını tutmak için tanımladığım sayaç.
       int website=0; //Web sitesi saysını tutmak için tanımladığım sayaç.
       int i=0; // Veri dizisini kullanmak için tanımladığım değişken.
            
       //Dosya Okuma işlemini yapıp ödevde istenilenleri buldurduğum kısım.
       try
       {
           //Dosyayı okuttuğum kısım.
           FileInputStream fStream= new FileInputStream("icerik.txt");
           try (DataInputStream dStream = new DataInputStream(fStream)) 
           {
             BufferedReader bReader= new BufferedReader(new InputStreamReader(dStream));
             
             // Bütün satırları okuması için kullandığım döngü.
             while( (metin= bReader.readLine()) != null)
             { 
               veri[i] = metin;
               i++;
               String[] dataArray= metin.split(" ");//Textin içindeki metni boşluğa göre ayırmasını sağladığım kısım.
                
               //Text içindeki boşluğa göre ayırdığım metni okumasını sağladığım döngü.
               for (String a : dataArray) 
               {
                  //Kelime sayısını bulmak için kullandığım regex.
                  String regex = "\\S+";
                  Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                  Matcher m = p.matcher(a);
                  if (m.matches()) 
                  {
                    kelime++;
                  }   
                  
                  //Cümle sayısını bulmak için kullandığım regex.
                  String regex1 = "[\"']?[A-Z][^.?!]+((?![.?!]['\"]?\\\\s[\"']?[A-Z][^.?!]))+[.?!'\"]";
                  Pattern p1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
                  Matcher m1 = p1.matcher(a);
                  if (m1.matches()) 
                  {
                    cumle++;
                  }   
                  
                  //Mail sayısını bulmak için kullandığım regex.
                  String regex2 = ".*(\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b).*";
                  Pattern p2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
                  Matcher m2 = p2.matcher(a);
                  if (m2.matches()) 
                  {
                    mail++;
                  }   
                  
                  //Web sitesi sayısını bulmak için kullandığım regex.
                  String regex3 = "^[^@]+\\.(com|edu|tr|org|com\\.tr|net\\\\.tr|org\\.tr|edu\\.tr)$";
                  Pattern p3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
                  Matcher m3 = p3.matcher(a);
                  if (m3.matches()) 
                  {
                    website++;
                  }   
                  
               }
                
                // Sesli harf sayısını bulmak için kulllandığım döngü ve koşul yapısı.
               for (int b=0; b<metin.length(); b++)
               {
                if(metin.charAt(b)== 'a' || metin.charAt(b)== 'A' || metin.charAt(b)== 'e' || metin.charAt(b)== 'E'
                || metin.charAt(b)== 'ı' || metin.charAt(b)== 'I' || metin.charAt(b)== 'i' || metin.charAt(b)== 'İ'
                || metin.charAt(b)== 'o' || metin.charAt(b)== 'O' || metin.charAt(b)== 'ö' || metin.charAt(b)== 'Ö'
                || metin.charAt(b)== 'u' || metin.charAt(b)== 'U' || metin.charAt(b)== 'ü' || metin.charAt(b)== 'Ü') 
                {
                    sesliHarf++;
                }
               }  
             }     
           }
        }    
             //Hata olursa hangi hatadan kaynaklandığını gösterecek olan kısım.
             catch(IOException e)
             {
                System.err.println("Hata:" + e.getMessage());
             } 
       
                // Ekrana yazma işlemlerini yaptığım kod satırları.
                System.out.println("Toplam Sesli Harf Sayısı:" + sesliHarf );
                System.out.println("Toplam Kelime Sayısı:" + kelime );
                System.out.println("Toplam Cümle Sayısı:" + cumle);
                System.out.println("Toplam Mail Sayısı:" + mail );
                System.out.println("Toplam Web Sitesi Sayısı:" + website );
                
    }
   
    public static void main(String[] args) 
    {
        regexHesaplama();
    }       
}