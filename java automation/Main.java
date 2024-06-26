package main;

import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static HashMap<String, String> users = new HashMap<>();//Kullanicilari saklamak icin
    private static HashMap<String, Integer> balances = new HashMap<>(); // Bakiyeleri saklamak icin
    private static HashMap<String, List<String>> sessions = new HashMap<>();//Kullanicilarin seanslarini saklamak icin
    private static HashMap<String, String[]> filminformation = new HashMap<>();//film bilgileri
    //private static List<String> comments = new ArrayList<>();
    private static HashMap<String, List<String>> comments = new HashMap<>();//yorumlar
    
    static {// direkt statik baslamasi icin filmler func alınmadı!!
         // FİLM1 bilgilerini ekle
String[] film1Bilgileri = {"Ex Machine", "Bir internet sirketinin yazilim muhendisi olan Caleb Smith'in (Domhnall Gleeson), sirketin zeki ve gizemli CEO'su Nathan Bateman'in (Oscar Isaac) yarattigi yapay zeka robotu Ava'yi (Alicia Vikander) test etmesini anlatiyor.","Caleb Smith,Ava,Nathan Bateman,Kyoko,Jasmine,Amber","13.00-15.00","16.00-18.00","15","43","150"};
filminformation.put("Ex Machine", film1Bilgileri);

// FİLM2 bilgilerini ekle
String[] film2Bilgileri = {"Ataturk", "Mustafa Kemal Ataturk'un hayatini, mucadelelerini ve Turkiye Cumhuriyeti'ni kurma surecini anlatiyor.","Aras Bulut İynemli, Songül Öden, Mehmet Günsür, Sarp Akkaya, Berk Cankat, Esra Bilgiç, Sahra Şaş, Bertan Asllani, Beran Kotan, Hamdi Alkan, Emma Watson, Darko Peri","14.00-17.00","18.00-20.00","53","78","35"};
filminformation.put("Ataturk", film2Bilgileri);

// FİLM3 bilgilerini ekle
String[] film3Bilgileri = {"Buz Devri", "Buzul caginin basladigi bir donemde, bir mamut, bir kilic disli kaplan, bir miskin ve bir insan bebegin maceralarini anlatiyor.","John Leguizamo, Ray Romano, Chris Wedge, Denis Leary, Jack Black, Alan Tudyk","10.00-11.30","12.00-13.30","30","90","100"};
filminformation.put("Buz Devri", film3Bilgileri);

// FİLM4 bilgilerini ekle
String[] film4Bilgileri = {"Joker", "Gotham City'de yasayan basarisiz bir komedyen olan Arthur Fleck'in (Joaquin Phoenix), toplum tarafindan dislanmasina ve zorbaliga ugramasina dayanamayarak, psikolojik olarak cokuse gecmesini ve kendisini suc ve kaosun icinde bulan Joker karakterine donusmesini anlatiyor.","Joaquin Phoenix, Robert De Niro, Zazie Beetz, Bryan Callen, Frances Conroy,Glenn Freshler","18.00-20.00", "19.30-21.30","101","200","300"};
filminformation.put("Joker", film4Bilgileri);

// FİLM5 bilgilerini ekle
String[] film5Bilgileri = {"Minions", "Tarihin baslangicindan itibaren var olan ve kotu bir efendiye hizmet etmek isteyen sari renkli yaratiklar olan minionlarin maceralarini anlatiyor.","Pierre Coffin, Chris Meledandri, Sandra Bullock, Jon Hamm","14.00-15.30","16.00-17.30","90","46","125"};
filminformation.put("Minions", film5Bilgileri);

comments.put("Minions", new ArrayList<>());
comments.put("Ex Machine", new ArrayList<>());
comments.put("Joker", new ArrayList<>());
comments.put("Ataturk", new ArrayList<>());
comments.put("Buz Devri", new ArrayList<>());
List<String> userSessions = new ArrayList<>();// kullanıcı yorumları
}



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //scanf yerine her yere scanner çağır ?!?!
        String loggedInUsername = null;

        while (true) {
            System.out.println("[1] Kayıt Ol");
            System.out.println("[2] Giriş Yap");
            System.out.println("[3] Filmler");
            System.out.println("[0] Cıkıs");
            System.out.print("Seçiminizi yapınız: ");
            int sec = scanner.nextInt();
        // loginuser fonksiyonunu scannerda girilen veriye göre işle
            switch (sec) {
                case 1:
                System.out.print("\033[H\033[2J"); //system clear
                System.out.flush();
                    registerUser(scanner);
                    break;
                case 3: filmler();break;
                case 2:
                System.out.print("\033[H\033[2J"); //system clear
                System.out.flush();
                loggedInUsername = loginUser(scanner);
                    if(loggedInUsername!=null){
                        int menuSecim;
                        do{
                            menuSecim=menu(scanner,loggedInUsername);
                            switch(menuSecim){
                                case 1: bakiyeekle(scanner, loggedInUsername);break;
                                case 2: seans(scanner,loggedInUsername,filminformation);break;
                                case 3: yorumyap(scanner,loggedInUsername,filminformation);break;
                                case 4: bilgiler(loggedInUsername);break;
                                case 5: System.out.print("\033[H\033[2J"); //system clear
                                System.out.flush();break;
                                case 0:
                                System.out.print("\033[H\033[2J"); //system clear
                                System.out.flush();
                                                    
                                System.out.println("*******************************");
                                System.out.println("*                             *");
                                System.out.println("*          HosCakal!          *");
                                //System.out.println("*         "+username+"               ");
                                System.out.println("*                             *");
                                System.out.println("*******************************");
                                //System.out.println("Programdan çıkılıyor...");
                                try {
                                    Thread.sleep(2000); // 2 saniye sleep
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.exit(0);
                                break;
                            }
                        } while(menuSecim!=5);
                    }
                    break;
                case 0:
                System.out.print("\033[H\033[2J"); //system clear
                System.out.flush();
                    System.out.println("*******************************");
                    System.out.println("*                             *");
                    System.out.println("*          HosCakal!          *");
                    //System.out.println("*         "+username+"               ");
                    System.out.println("*                             *");
                    System.out.println("*******************************");
                    //System.out.println("Programdan çıkılıyor...");
                    try {
                        Thread.sleep(2000); // 2 saniye sleep
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
/*START REGISTER FUNC START */
    private static void registerUser(Scanner scanner) {
        System.out.print("Kullanıcı adınızı giriniz: ");
        String username = scanner.next();//scannerdan girilen veriyi usarnameye atar
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.next();//scannerdan girilen veriyi password atar

        if (users.containsKey(username)) {//containsKey username olup olmadıgını kontrol eder varsa true doner!
            System.out.print("\033[H\033[2J"); //system clear
            System.out.flush();
            System.out.println("Bu kullanıcı adı zaten mevcut. Lütfen başka bir kullanıcı adı seçin.");
        } else {
            users.put(username, password);//users icine girilen username ve pass degerini ekle!
            balances.put(username,0);//kullanicinin bakiyesini 0'a esitle
            System.out.println("Kayıt başarılı. Giriş yapabilirsiniz.");
            System.out.print("\033[H\033[2J"); //system clear
            System.out.flush();
        }
    }
/*END REGISTER FUNC END */

/*START LOGİN FUNC START */
    //Scanneri çağır scanf'i çagır gibi ????
    private static String loginUser(Scanner scanner) {
        System.out.print("Kullanıcı adınızı giriniz: ");
        String username = scanner.next();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.next();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.print("\033[H\033[2J"); //system clear
            System.out.flush();
            System.out.println("*******************************");
            System.out.println("*                             *");
            System.out.println("*          Hosgeldin!         *");
            //System.out.println("*         "+username+"               ");
            System.out.println("*                             *");
            System.out.println("*******************************");
            try {
                Thread.sleep(2000); // 2 saniye sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J"); //system clear
            System.out.flush();



            System.out.println("Merhaba " + username + "!");
            return username;//username dışarıya döndür
        } else {
            System.out.println("Kullanıcı adı veya şifre hatalı. Tekrar deneyin.");
            return null;//hatalı ise null dönder
        }
        //Scanneri çağır??
    }
/*END LOGIN FUNC END */
/*START MENU FUNC START */

    public static int menu(Scanner scanner, String username) {
        System.err.println("--------------------------------");
        System.out.println("[1]Bakiye Islemleri");
        System.out.println("[2]Filmler");
        System.out.println("[3]YorumYap");
        System.out.println("[4]Bilgiler");
        System.out.println("[5]Giris Ekranına Don");
        System.out.println("[0]Cıkıs");
        System.out.print("Seçiminizi yapınız: ");
        System.err.println("\n--------------------------------");
        int secim = scanner.nextInt();
        scanner.nextLine(); // Boş satırı oku

        return secim;
    }
/*END MENU FUNC END */
/*START BILGILER FUNC START */

    public static void bilgiler(String username){
        int bakiye= balances.get(username);//bakiye cek
        List<String> userSessions = sessions.get(username);//seans cek
        System.out.println("Hosgeldin "+username+"!");
        System.out.println("Bakiyen: "+bakiye);
        if(userSessions==null){
            System.out.println("Seans bulunmamaktadır!");
        }
        else{
        for (String session : userSessions) {
            System.out.println(session);
        }}
        

    }
/*END BILGILER FUNC END */
/*START FILMLER FUNC START */
public static void filmler(){
    // TO DO LİST koltuk sayisi ekle!
   

for (String filmAdi : filminformation.keySet()) {
    System.out.println("Film Adı: " + filmAdi);
    
    // O anki film adına göre bilgileri al
    String[] filmBilgileri = filminformation.get(filmAdi);
    
    // Filmin tüm bilgilerini tek bir satırda yazdır
    System.out.println("Film Konusu: "+ filmBilgileri[1]);
    System.out.println("Oyuncular: " + filmBilgileri[2]);
    System.out.println("Seanslar: \n\n" + filmBilgileri[3] + "\nKoltuk sayisi: "+filmBilgileri[5] +"\n\n"+ filmBilgileri[4]+"\nKoltuk sayisi: " + filmBilgileri[6]);
    System.out.println("Bilet Fiyati: " + filmBilgileri[7]);
    System.out.println("-----------------------------------");
}


/* 
    for(int i=0;i<5;i++){
      

        System.out.println("\n---------------FILM " + (i+1) + "---------------");
        System.out.println("Film adi: " + filmBilgileri[i][0]);
        System.out.println("Film Konusu: " + filmBilgileri[i][1]);
        System.out.println("Film Oyunculari: " + filmBilgileri[i][2]);
        System.out.println("Film Seanslari: " + filmBilgileri[i][3]);
        System.out.println("Bilet Fiyati: " + filmBilgileri[i][4]);
    }*/
}

/*END FILMLER FUNC END */
/*START BAKIYE EKLE FUNC START */

public static void bakiyeekle(Scanner scanner, String username) {
    int bakiye = balances.get(username);
    System.out.print("\033[H\033[2J"); //system clear
    System.out.flush();
    System.out.println("Bakiye Islemleri!");
    System.out.println("Bakiyen: "+balances.get(username));
    System.out.println("-----------------------------");
    System.out.println("[1]Bakiye Ekle");
    System.out.println("[2]Bakiye Cıkart");
    System.out.println("[0]Geri Gel");
    System.out.println("Islem Sec:");
    int islem = scanner.nextInt();
    switch(islem){
        case 0: break;
        case 1:
        System.out.print("\033[H\033[2J");  
            System.out.flush();
        System.out.print("Eklenecek bakiye miktarını giriniz: ");
        int miktar = scanner.nextInt();
        if(miktar<=0){
            System.out.println("Gecersiz Bakiye Miktari!");
            return;
        }    
        if(balances.containsKey(username)){ //bakiyesi varsa true doner 
            balances.put(username, bakiye + miktar);
        }
        else{
            balances.put(username,bakiye);
        }
          
        System.out.println("Bakiye ekleme işlemi başarılı!\nYeni bakiye: " + balances.get(username));break;
        case 2:
        System.out.print("\033[H\033[2J");  
            System.out.flush();
        System.out.print("Cekilecek miktarı giriniz: ");
        int cıkarmiktar = scanner.nextInt();
        if(cıkarmiktar>bakiye ){
            System.out.println("Gecersiz Bakiye Miktari!");
            return;
        }    
        if(balances.containsKey(username) && cıkarmiktar>0){
            balances.put(username, bakiye - cıkarmiktar);
        }
        else{
            balances.put(username,bakiye);
        }
          
        System.out.println("Bakiye ekleme işlemi başarılı!\nYeni bakiye: " + balances.get(username));break;

    }
    
}
/*END BAKIYE EKLE FUNC END */


public static void seans(Scanner scanner,String username,HashMap<String, String[]> filminformation){
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
    int kullanicibakiye=balances.get(username);

    int i=1;
    for (String filmAdi : filminformation.keySet()) {//filminformation içindeki direkt film adını alır  
        System.out.println("["+i+"]" + filmAdi);
        i++;
    }
    System.out.println("[0]Geri Gel");
    System.out.print("Islem Sec: ");
    int filmsec= scanner.nextInt();
    switch(filmsec) {
        case 1:
    String[] filmBilgileri = filminformation.get("Minions");
    int filmfiyat = Integer.parseInt(filmBilgileri[7]);
    System.out.print("\033[H\033[2J");
    System.out.println("Film Bilgileri");
    System.out.println("-----------------------------------");
    System.out.println("Film Adi: " + filmBilgileri[0]);
    System.out.println("Film Konusu: " + filmBilgileri[1]);
    System.out.println("Oyuncular: " + filmBilgileri[2]);
    System.out.println("Seanslar: \n\n" + filmBilgileri[3] + "\nKoltuk sayisi: "+filmBilgileri[5] +"\n\n"+ filmBilgileri[4]+"\nKoltuk sayisi: " + filmBilgileri[6]);
    System.out.println("Bilet Fiyatlari: " + filmBilgileri[7]);
    System.out.println("-----------------------------------");
    System.out.println("Islemler");
    System.out.println("[1] Seans Sec");
    System.out.println("[0] Geri Gel");
    System.out.println("Islem Sec: ");
    int islem = scanner.nextInt();
    switch(islem) {
        case 0:
            break;
        case 1:
            System.out.print("\033[H\033[2J"); // Ekranı temizle
            System.out.flush();
            System.out.println("Seanslar: \n\n" + "[1]"+filmBilgileri[3] + "\nKoltuk sayisi: "+filmBilgileri[5] +"\n\n"+ "[2]"+filmBilgileri[4]+"\nKoltuk sayisi: " + filmBilgileri[6]+"\n\n"+"[0]Geri Gel");

            System.out.println("Seans Seç:");
            int seanssec = scanner.nextInt();
            String sessionTime = "";
            if(seanssec== 0){
                return;
            }
            if(seanssec==1){
                int filmFiyat = Integer.parseInt(filmBilgileri[7]);
                if(kullanicibakiye<filmFiyat){
                    System.out.println("Yetersiz Bakiye!");
                }
                else{
                    String seans1="Minions 14.00-15.30";
                    balances.put(username, balances.get(username) - filmFiyat);
                    List<String> userSessions = sessions.get(username);//seansların listesini cek
                    if(userSessions==null){
                        userSessions = new ArrayList<>();
                    }
                    userSessions.add(seans1);//seans1 pushla
                    sessions.put(username, userSessions);//girisi bellege ekle
                    int yeniDeger = Integer.parseInt(filmBilgileri[5]) - 1;//ks eksilt
                    filmBilgileri[5] = String.valueOf(yeniDeger);//yeni degeri degistir
                    System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                }

            }
            else if(seanssec==2){
                int filmFiyat = Integer.parseInt(filmBilgileri[7]);
                if(kullanicibakiye<filmFiyat){
                    System.out.println("Yetersiz Bakiye!");
                }
                else{
                    String seans1="Minions 16.00-17.30";
                    balances.put(username, balances.get(username) - filmFiyat);
                    List<String> userSessions = sessions.get(username);//seansların listesini cek
                    if(userSessions==null){
                        userSessions = new ArrayList<>();
                    }
                    userSessions.add(seans1);//seans1 pushla
                    sessions.put(username, userSessions);//girisi bellege ekle
                    int yeniDeger = Integer.parseInt(filmBilgileri[6]) - 1;//ks eksilt
                    filmBilgileri[6] = String.valueOf(yeniDeger);//yeni degeri degistir
                    System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                }
            }
            
            break;
    }
    break;
            case 2:
            String[] filmBilgileri2 = filminformation.get("Ex Machine");
            int filmfiyat2 = Integer.parseInt(filmBilgileri2[7]);
            System.out.print("\033[H\033[2J");
            System.out.println("Film Bilgileri");
            System.out.println("-----------------------------------");
            System.out.println("Film Adi: " + filmBilgileri2[0]);
            System.out.println("Film Konusu: " + filmBilgileri2[1]);
            System.out.println("Oyuncular: " + filmBilgileri2[2]);
            System.out.println("Seanslar: \n\n" + filmBilgileri2[3] + "\nKoltuk sayisi: " + filmBilgileri2[5] + "\n\n"+ filmBilgileri2[4]+"\nKoltuk sayisi: " + filmBilgileri2[6]);
            System.out.println("Bilet Fiyatlari: " + filmBilgileri2[7]);
            System.out.println("-----------------------------------");
            System.out.println("Islemler");
            System.out.println("[1] Seans Sec");
            System.out.println("[0] Geri Gel");
            System.out.println("Islem Sec: ");
            int islem2 = scanner.nextInt();
            switch(islem2) {
                case 0:
                    break;
                    case 1:
                    System.out.print("\033[H\033[2J"); // Ekranı temizle
                    System.out.flush();
                    System.out.println("Seanslar: \n\n" + "[1]"+filmBilgileri2[3] + "\nKoltuk sayisi: "+filmBilgileri2[5] +"\n\n"+ "[2]"+filmBilgileri2[4]+"\nKoltuk sayisi: " + filmBilgileri2[6]+"\n\n"+"[0]Geri Gel");
                    
                    System.out.println("Seans Seç:");
                    int seanssec = scanner.nextInt();
                    String sessionTime = "";
                    if(seanssec== 0){
                        return;
                    }
                    if(seanssec==1){
                        int filmFiyat2 = Integer.parseInt(filmBilgileri2[7]);
                        if(kullanicibakiye<filmFiyat2){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Ex Machine 13.00-15.00";
                            balances.put(username, balances.get(username) - filmFiyat2);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri2[5]) - 1;//ks eksilt
                            filmBilgileri2[5] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));
        
                        }
        
                    }
                    else if(seanssec==2){
                        int filmFiyat2 = Integer.parseInt(filmBilgileri2[7]);
                        if(kullanicibakiye<filmFiyat2){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Ex Machine 16.00-18.00";
                            balances.put(username, balances.get(username) - filmFiyat2);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri2[6]) - 1;//ks eksilt
                            filmBilgileri2[6] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));
        
                        }
                    }
                    break;
                
            }//switchislem kapanis
            break;
            case 3:
            String[] filmBilgileri3 = filminformation.get("Joker");
            int filmfiyat3 = Integer.parseInt(filmBilgileri3[7]);
            System.out.print("\033[H\033[2J");
            System.out.println("Film Bilgileri");
            System.out.println("-----------------------------------");
            System.out.println("Film Adi: " + filmBilgileri3[0]);
            System.out.println("Film Konusu: " + filmBilgileri3[1]);
            System.out.println("Oyuncular: " + filmBilgileri3[2]);
            System.out.println("Seanslar: \n\n" + filmBilgileri3[3] + "\nKoltuk sayisi: " + filmBilgileri3[5] + "\n\n"+ filmBilgileri3[4]+"\nKoltuk sayisi: " + filmBilgileri3[6]);
            System.out.println("Bilet Fiyatlari: " + filmBilgileri3[7]);
            System.out.println("-----------------------------------");
            System.out.println("Islemler");
            System.out.println("[1] Seans Sec");
            System.out.println("[0] Geri Gel");
            System.out.println("Islem Sec: ");
            int islem3 = scanner.nextInt();
            switch(islem3) {
                case 0:
                    break;
                    case 1:
                    System.out.print("\033[H\033[2J"); // Ekranı temizle
                    System.out.flush();
                    System.out.println("Seanslar: \n\n" + "[1]"+filmBilgileri3[3] + "\nKoltuk sayisi: "+filmBilgileri3[5] +"\n\n"+ "[2]"+filmBilgileri3[4]+"\nKoltuk sayisi: " + filmBilgileri3[6]+"\n\n"+"[0]Geri Gel");
                    
                    System.out.println("Seans Seç:");
                    int seanssec = scanner.nextInt();
                    String sessionTime = "";
                    if(seanssec== 0){
                        return;}
                    if(seanssec==1){
                        int filmFiyat3 = Integer.parseInt(filmBilgileri3[7]);
                        if(kullanicibakiye<filmFiyat3){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Joker 18.00-20.00";
                            balances.put(username, balances.get(username) - filmFiyat3);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri3[5]) - 1;//ks eksilt
                            filmBilgileri3[5] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }

                    }
                    else if(seanssec==2){
                        int filmFiyat3 = Integer.parseInt(filmBilgileri3[7]);
                        if(kullanicibakiye<filmFiyat3){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Joker 19.00-21.30";
                            balances.put(username, balances.get(username) - filmFiyat3);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri3[6]) - 1;//ks eksilt
                            filmBilgileri3[6] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }
                    }
                    break;
                
            }//switchislem kapanis
            break;
            case 4:
            String[] filmBilgileri4 = filminformation.get("Ataturk");
            int filmfiyat4 = Integer.parseInt(filmBilgileri4[7]);
            System.out.print("\033[H\033[2J");
            System.out.println("Film Bilgileri");
            System.out.println("-----------------------------------");
            System.out.println("Film Adi: " + filmBilgileri4[0]);
            System.out.println("Film Konusu: " + filmBilgileri4[1]);
            System.out.println("Oyuncular: " + filmBilgileri4[2]);
            System.out.println("Seanslar: \n\n" + filmBilgileri4[3] + "\nKoltuk sayisi: " + filmBilgileri4[5] + "\n\n"+ filmBilgileri4[4]+"\nKoltuk sayisi: " + filmBilgileri4[6]);
            System.out.println("Bilet Fiyatlari: " + filmBilgileri4[7]);
            System.out.println("-----------------------------------");
            System.out.println("Islemler");
            System.out.println("[1] Seans Sec");
            System.out.println("[0] Geri Gel");
            System.out.println("Islem Sec: ");
            int islem4 = scanner.nextInt();
            switch(islem4) {
                case 0:
                    break;
                    case 1:
                    System.out.print("\033[H\033[2J"); // Ekranı temizle
                    System.out.flush();
                    System.out.println("Seanslar: \n\n" + "[1]"+filmBilgileri4[3] + "\nKoltuk sayisi: "+filmBilgileri4[5] +"\n\n"+ "[2]"+filmBilgileri4[4]+"\nKoltuk sayisi: " + filmBilgileri4[6]+"\n\n"+"[0]Geri Gel");
                    
                    System.out.println("Seans Seç:");
                    int seanssec = scanner.nextInt();
                    String sessionTime = "";
                    if(seanssec== 0){
                        return;}
                    if(seanssec==1){
                        int filmFiyat4 = Integer.parseInt(filmBilgileri4[7]);
                        if(kullanicibakiye<filmFiyat4){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Ataturk 14.00-17.00";
                            balances.put(username, balances.get(username) - filmFiyat4);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri4[5]) - 1;//ks eksilt
                            filmBilgileri4[5] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }

                    }
                    else if(seanssec==2){
                        int filmFiyat4 = Integer.parseInt(filmBilgileri4[7]);
                        if(kullanicibakiye<filmFiyat4){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Ataturk 18.00-20.00";
                            balances.put(username, balances.get(username) - filmfiyat4);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri4[6]) - 1;//ks eksilt
                            filmBilgileri4[6] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }
                    }
                    break;
                
            }//switchislem kapanis
            break;
            case 5:
            String[] filmBilgileri5 = filminformation.get("Buz Devri");
            int filmfiyat5 = Integer.parseInt(filmBilgileri5[7]);
            System.out.print("\033[H\033[2J");
            System.out.println("Film Bilgileri");
            System.out.println("-----------------------------------");
            System.out.println("Film Adi: " + filmBilgileri5[0]);
            System.out.println("Film Konusu: " + filmBilgileri5[1]);
            System.out.println("Oyuncular: " + filmBilgileri5[2]);
            System.out.println("Seanslar: \n\n" + filmBilgileri5[3] + "\nKoltuk sayisi: " + filmBilgileri5[5] + "\n\n"+ filmBilgileri5[4]+"\nKoltuk sayisi: " + filmBilgileri5[6]);
            System.out.println("Bilet Fiyatlari: " + filmBilgileri5[7]);
            System.out.println("-----------------------------------");
            System.out.println("Islemler");
            System.out.println("[1] Seans Sec");
            System.out.println("[0] Geri Gel");
            System.out.println("Islem Sec: ");
            int islem5 = scanner.nextInt();
            switch(islem5) {
                case 0:
                    break;
                    case 1:
                    System.out.print("\033[H\033[2J"); // Ekranı temizle
                    System.out.flush();
                    System.out.println("Seanslar: \n\n" + "[1]"+filmBilgileri5[3] + "\nKoltuk sayisi: "+filmBilgileri5[5] +"\n\n"+ "[2]"+filmBilgileri5[4]+"\nKoltuk sayisi: " + filmBilgileri5[6]+"\n\n"+"[0]Geri Gel");
                    
                    System.out.println("Seans Seç:");
                    int seanssec = scanner.nextInt();
                    String sessionTime = "";
                    if(seanssec== 0){
                        return;}
                    if(seanssec==1){
                        int filmFiyat5 = Integer.parseInt(filmBilgileri5[7]);
                        if(kullanicibakiye<filmfiyat5){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Buz Devri 10.00-11.30";
                            balances.put(username, balances.get(username) - filmfiyat5);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri5[5]) - 1;//ks eksilt
                            filmBilgileri5[5] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }

                    }
                    else if(seanssec==2){
                        int filmFiyat5 = Integer.parseInt(filmBilgileri5[7]);
                        if(kullanicibakiye<filmFiyat5){
                            System.out.println("Yetersiz Bakiye!");
                        }
                        else{
                            String seans1="Buz Devri 12.00-13.30";
                            balances.put(username, balances.get(username) - filmFiyat5);
                            List<String> userSessions = sessions.get(username);//seansların listesini cek
                            if(userSessions==null){
                                userSessions = new ArrayList<>();
                            }
                            userSessions.add(seans1);//seans1 pushla
                            sessions.put(username, userSessions);//girisi bellege ekle
                            int yeniDeger = Integer.parseInt(filmBilgileri5[6]) - 1;//ks eksilt
                            filmBilgileri5[6] = String.valueOf(yeniDeger);//yeni degeri degistir
                            System.out.println("Bilet satın alındı. Yeni bakiyeniz: " + balances.get(username));

                        }
                    }
                    break;
                
            }//switchislem kapanis
            break;

    }//switch filmsec kapanis
}//func kapanis

public static void yorumyap(Scanner scanner,String username,HashMap<String, String[]> filminformation){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    int i=1;
    for (String filmAdi : filminformation.keySet()) {//filminformation içindeki direkt film adını alır  
        System.out.println("["+i+"]" + filmAdi);
        i++;
    }
    System.out.print("Film Sec: ");
    int filmsec= scanner.nextInt();
    switch(filmsec){
        case 1: 
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Minions Icin");
            System.out.println("[1]Yorum Yap");
            System.out.println("[2]Yorumlari Görüntüle");
            System.out.println("[0]Geri Gel");
            System.out.println("Islem Sec");
            int islem = scanner.nextInt();
            if(islem==1){
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println("Yorum Yap: ");
                String yorum = scanner.next();
                comments.get("Minions").add(yorum);
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
            }
        else if(islem==2){
        List<String> yorumlar = comments.get("Minions"); 
        System.out.println("Minions Yorumlar:");
            if(yorumlar==null){
                System.out.println("Yorum Bulunmuyor");
                return;
            }
        for (String yorum : yorumlar) {
            //system clear ekleme yoksa girdiler gozukmez!!!!!
            System.out.println("-"+yorum);
        }}
        else if(islem==0){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            return;
        }
        else{
            System.out.println("Gecersiz Islem!");
            return;
        }break;
        case 2: 
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Ex Machine Icin");
        System.out.println("[1]Yorum Yap");
        System.out.println("[2]Yorumlari Görüntüle");
        System.out.println("[0]Geri Gel");
        System.out.println("Islem Sec");
        int islem2 = scanner.nextInt();
        if(islem2==1){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Yorum Yap: ");
            String yorum = scanner.next();
            comments.get("Ex Machine").add(yorum);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
    else if(islem2==2){
    List<String> yorumlar = comments.get("Ex Machine");
    System.out.println("Ex Machine Yorumlar:");

        if(yorumlar==null){
            System.out.println("Yorum Bulunmuyor");
            return;
    }
    for (String yorum : yorumlar) {
        //system clear ekleme yoksa girdiler gozukmez!!!!!
        System.out.println("-"+yorum);
    }}
    else if(islem2==0){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        return;
    }
    else{
        System.out.println("Gecersiz Islem!");
    }break;
    case 3: 
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Joker Icin");
        System.out.println("[1]Yorum Yap");
        System.out.println("[2]Yorumlari Görüntüle");
        System.out.println("[0]Geri Gel");
        System.out.println("Islem Sec");
        int islem3 = scanner.nextInt();
        if(islem3==1){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Yorum Yap: ");
            String yorum = scanner.next();
            comments.get("Joker").add(yorum);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
    else if(islem3==2){
    List<String> yorumlar = comments.get("Joker"); 
    if(yorumlar==null){
        System.out.println("Yorum Bulunmuyor");
        return;
    }
    for (String yorum : yorumlar) {
        System.out.println("Joker Yorumlar:");
        //system clear ekleme yoksa girdiler gozukmez!!!!!
        System.out.println("-"+yorum);
    }}
    else if(islem3==0){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        return;
    }
    else{
        System.out.println("Gecersiz Islem!");
    }break;
    case 4: 
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Ataturk Icin");
        System.out.println("[1]Yorum Yap");
        System.out.println("[2]Yorumlari Görüntüle");
        System.out.println("[0]Geri Gel");
        System.out.println("Islem Sec");
        int islem4 = scanner.nextInt();
        if(islem4==1){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Yorum Yap: ");
            String yorum = scanner.next();
            comments.get("Ataturk").add(yorum);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
    else if(islem4==2){
    List<String> yorumlar = comments.get("Ataturk");
    if(yorumlar==null){
        System.out.println("Yorum Bulunmuyor");
        return;
    }
    for (String yorum : yorumlar) {
        System.out.println("Ataturk Yorumlar:");
        //system clear ekleme yoksa girdiler gozukmez!!!!!
        System.out.println("-"+yorum);
    }}
    else if(islem4==0){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        return;
    }
    else{
        System.out.println("Gecersiz Islem!");
    }break;
    case 5: 
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Buz Devri Icin");
        System.out.println("[1]Yorum Yap");
        System.out.println("[2]Yorumlari Görüntüle");
        System.out.println("[0]Geri Gel");
        System.out.println("Islem Sec");
        int islem5 = scanner.nextInt();
        if(islem5==1){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Yorum Yap: ");
            String yorum = scanner.next();
            comments.get("Buz Devri").add(yorum);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
    else if(islem5==2){
    List<String> yorumlar = comments.get("Buz Devri");
    if(yorumlar==null){
        System.out.println("Yorum Bulunmuyor");
        return;
    }
    for (String yorum : yorumlar) {
        System.out.println("Buz Devri Yorumlar:");
        //system clear ekleme yoksa girdiler gozukmez!!!!!
        System.out.println("-"+yorum);
    }}
    else if(islem5==0){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        return;
    }
    else{
        System.out.println("Gecersiz Islem!");
    }break;
    }//switch kapanis!

}


}// MAIN KAPANIS






// BİLGİLER!
// bir void te scanf kulalnıcaksan parametre olarak Scanner scanner ver!
// String password = users.get(username); veri cekeceksen users.get kullan
  //bilet fiyatini alirken kullan!!
  //int koltukSayisi = Integer.parseInt(filmBilgileri[i][4]);//integer.parseint string'i integer'e donusturur!

/*TO DO LİST 
//Bakiye ekle* 
//Seans al*
//Yorum yap*
//Bilgiler*
// tum filmler icin yap*
// Yorumlar kismina yorumlari goruntule film adi ekle*
//Bakiye islemleri baslik ekle
// Seanslarim null bilgisini duzelt!*
// Seanslarımı list yap yoksa diger seanslaım gozukmuyor*
// Islem secildikten sonra ne sectigini yazdir!

*/  
