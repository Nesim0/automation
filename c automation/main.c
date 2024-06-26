#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>//for sleep func

#define MAX_KULLANICI 100
struct Kullanici {
    char kullaniciAdi[20];
    char sifre[20];
    float bakiye;
    char biletlerim[1000];
};
//kullanici adi var mi yok mu fonksiyonu

void dosyayaKullaniciBilgileriniKaydet(struct Kullanici *kullanicilar, int kullaniciIndeks) {
    FILE *dosya = fopen("kullanicilar.txt", "a"); // "a" modunda dosyayı aç
    if (dosya == NULL) {
        printf("Dosya acilamadi!");
        exit(1);
    }

    fprintf(dosya, "%s %s %.2f %s", kullanicilar[kullaniciIndeks].kullaniciAdi, kullanicilar[kullaniciIndeks].sifre, kullanicilar[kullaniciIndeks].bakiye,kullanicilar[kullaniciIndeks].biletlerim);
    fprintf(dosya,"\n");
    // Buraya diğer kullanıcı bilgilerini ekleyebilirsiniz

    fclose(dosya);
}
int kullaniciVarMi(struct Kullanici *kullanicilar, int kullaniciSayisi, const char *kullaniciAdi) {
    int i;
    for (i = 0; i < kullaniciSayisi; i++) {
        if (strcmp(kullaniciAdi, kullanicilar[i].kullaniciAdi) == 0) {
            return 1; // Kullanıcı adı mevcut
        }
    }
    return 0; // Kullanıcı adı mevcut değil
}

void kayitOl(struct Kullanici *kullanicilar, int *kullaniciSayisi) {
    if (*kullaniciSayisi < MAX_KULLANICI) {
        printf("*****KAYIT EKRANI*****\n");
        printf("Kullanici Adi: ");
        char yeniKullaniciAdi[20];
        scanf("%s", yeniKullaniciAdi);

        // Kullanıcı adı kontrolü
        if (kullaniciVarMi(kullanicilar, *kullaniciSayisi, yeniKullaniciAdi)) {
            printf("Bu kullanici adi zaten mevcut. Lutfen baska bir kullanici adi secin.\n");
        } else {
            printf("Sifre: ");
            char yeniSifre[20];
            scanf("%s", yeniSifre);

            // Dosyaya yazma işlemi
            FILE *dosya = fopen("kullanicilar.txt", "a");
            if (dosya == NULL) {
                printf("Dosya acilamadi.\n");
                return;
            }
            fprintf(dosya, "\n%s %s %.2f\n", yeniKullaniciAdi, yeniSifre, kullanicilar->bakiye);
            fclose(dosya);
            strcpy(kullanicilar[*kullaniciSayisi].kullaniciAdi, yeniKullaniciAdi);
            strcpy(kullanicilar[*kullaniciSayisi].sifre, yeniSifre);

            printf("Kayit basarili!\n");
            (*kullaniciSayisi)++;
        }
    } else {
        printf("Maksimum kullanici sayisina ulasildi. Yeni kayit yapilamaz.\n");
    }
}

int girisYap(struct Kullanici *kullanicilar, int kullaniciSayisi) {
    char girilenKullaniciAdi[20];
    char girilenSifre[20];
    printf("*****GIRIS YAP*****\n");

    printf("Kullanici Adi: ");
    scanf("%s", girilenKullaniciAdi);

    printf("Sifre: ");
    scanf("%s", girilenSifre);
    int i;
    for (i = kullaniciSayisi - 1; i >= 0; i--) { // Tersten tarama
        if (strcmp(girilenKullaniciAdi, kullanicilar[i].kullaniciAdi) == 0 &&
            strcmp(girilenSifre, kullanicilar[i].sifre) == 0) {
            printf("Giris basarili!\n");
            return i; // Kullanıcının dizideki indeksini döndür
        }
    }

    printf("Hatali kullanici adi veya sifre!\n");
    return -1; // Giriş başarısız
}

// Bakiye Ekleme
void Bakiyekle(struct Kullanici *kullanici) { //Kullanici struct'ını kullanici olarak cagir
    float miktar;
    system("cls");
    printf("Eklenecek Miktar: ");
    scanf("%f",&miktar);
    kullanici->bakiye += miktar;// kullanıcının bakiyesine miktari ekle
    printf("Yeni Bakiyeniz: %.2f\n", kullanici->bakiye);//kullanici bakiyesini yazdir
    printf("Isleminiz Basariyla Tamamlanmistir.\n\n");
}

// Bakiye Cıkarma
void Bakiyecikar(struct Kullanici *kullanici) {
    float miktar;
    system("cls");
    printf("Cikarilacak Miktar: ");
    scanf("%f", &miktar);
    // Eger bakiye kucukse cekicelecek miktardan onay verme
    if (kullanici->bakiye < miktar) {// eğer kullanıcının bakiyesi kücükse miktardan
        printf("Cekmek Istedigin Miktar Bakiyenden Yuksek!\nBakiyen: %.2f\n\n", kullanici->bakiye);
    }
    // Eger cekilecek miktar bakiyeden kucukse devam et
    else {// eğer kullanıcının bakiyesi büyükse miktardan
        kullanici->bakiye -= miktar;
        printf("Yeni Bakiyeniz: %.2f\n", kullanici->bakiye);
        printf("Isleminiz Basariyla Tamamlanmistir.\n\n");
    }
}

// Bakiye Yukleme Ekrani
void BakiyeYukle(struct Kullanici *kullanicilar, int kullaniciIndeks) {
    int islem;
    system("cls");
    printf("*****Bakiye Yukleme Ekrani*****\n");
    printf("Bakiyeniz: %.2f\n", kullanicilar[kullaniciIndeks].bakiye);
    printf("[1]Para Ekle\n");
    printf("[2]Para Cek\n");
    printf("[0]Geri Gel\n");
    printf("Islem Sec:");
    scanf("%d", &islem);
    switch (islem) {
    case 1:
        Bakiyekle(&kullanicilar[kullaniciIndeks]);//satır 535 kullaniciindeks=kullanıcısayisi
        break;
    case 2:
        Bakiyecikar(&kullanicilar[kullaniciIndeks]);//satır 535 kullaniciindeks=kullanıcısayisi
        break;
    case 0: ;break;
    default:
        printf("Yanlis İslem!\n");
        break;
    }
}
void filmlist(){
    /*---------------FİLM 1-------------------*/
    system("cls");
    printf("---------------FILM1---------------\n");
    char filmadi1[100]="Ex Machine";
    char filmkonusu1[1000]="Bir internet sirketinin yazilim muhendisi olan Caleb Smith'in (Domhnall Gleeson), sirketin zeki ve gizemli CEO'su Nathan Bateman'in (Oscar Isaac) yarattigi yapay zeka robotu Ava'yi (Alicia Vikander) test etmesini anlatiyor. Caleb, Ava'yi test ederken, Ava'nin gercek bir bilinc sahibi olup olmadigina karar vermeye calisir. Bu arada, Nathan'in gizli planlari ve Ava'nin kendisine olan ilgisi Caleb'i zor durumda birakir. Film, yapay zeka, bilinc, insanlik ve etik gibi konulara deginmektedir.";
    char filmoyuncular1[100]="Caleb Smith,Ava,Nathan Bateman,Kyoko,Jasmine,Amber";
    char filmseans1[200]="13.00-15.00";
    int  koltuksayisi1=150;
    int  biletfiyati1=75;
    printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm Seans:%s\nFilm koltuk sayisi:%d\nBilet fiyati:%d",filmadi1,filmkonusu1,filmoyuncular1,filmseans1,koltuksayisi1,biletfiyati1);
    /*---------------FİLM 2-------------------*/
    printf("\n---------------FILM2---------------\n");
    char filmadi2[1000]="Ataturk";
    char filmkonusu2[1000]="Mustafa Kemal Ataturk'un hayatini, mucadelelerini ve Turkiye Cumhuriyeti'ni kurma surecini anlatiyor. Film, Ataturk'un cocukluk yillarindan baslayarak, milli mucadelenin lideri olmaya giden yolda yasadigi olaylari, insani yonlerini ve karsilastigi zorluklari gozler onune seriyor. Filmde, Ataturk'un annesi Zubeyde Hanim, babasi Ali Riza Efendi, arkadaslari Ali Fuat Cebesoy, Ismet Inonu, Kazim Karabekir, dusmanlari Enver Pasa, Otto Liman von Sanders, Stiliyan Kovacev gibi tarihi kisilikler de yer aliyor. Film, iki bolumden olusuyor.";
    char filmoyuncular2[1000]="Aras Bulut İynemli, Songül Öden, Mehmet Günsür, Sarp Akkaya, Berk Cankat, Esra Bilgiç, Sahra Şaş, Bertan Asllani, Beran Kotan, Hamdi Alkan, Emma Watson, Darko Peri";
    char filmseans2[200]="14.00-17.00, 18.00-20.00";
    int  koltuksayisi2=35;
    int  biletfiyati2=50;
    printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm Seans:%s\nFilm koltuk sayisi:%d\nBilet fiyati:%d",filmadi2,filmkonusu2,filmoyuncular2,filmseans2,koltuksayisi2,biletfiyati2);
    /*---------------FİLM 3-------------------*/
    printf("\n---------------FILM3---------------\n");
    char filmadi3[1000]="Buz Devri";
    char filmkonusu3[1000]="Buzul caginin basladigi bir donemde, bir mamut, bir kilic disli kaplan, bir miskin ve bir insan bebegin maceralarini anlatiyor. Bu dort karakter, birlikte guneye dogru yol alirken, bir yandan sogukla, bir yandan da diger hayvanlarla mucadele ediyorlar. Film, komedi ve animasyon turunde bir yapimdir.";
    char filmoyuncular3[1000]="John Leguizamo, Ray Romano, Chris Wedge, Denis Leary, Jack Black, Alan Tudyk";
    char filmseans3[200]="10.00-11.30, 12.00-13.30";
    int  koltuksayisi3=2;
    int  biletfiyati3=25;
    printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm Seans:%s\nFilm koltuk sayisi:%d\nBilet fiyati:%d",filmadi3,filmkonusu3,filmoyuncular3,filmseans3,koltuksayisi3,biletfiyati3);
    /*---------------FİLM 4-------------------*/
    printf("\n---------------FILM4---------------\n");
    char filmadi4[1000]="Joker";
    char filmkonusu4[1000]="Gotham City'de yasayan basarisiz bir komedyen olan Arthur Fleck'in (Joaquin Phoenix), toplum tarafindan dislanmasina ve zorbaliga ugramasina dayanamayarak, psikolojik olarak cokuse gecmesini ve kendisini suc ve kaosun icinde bulan Joker karakterine donusmesini anlatiyor. Film, DC Comics'in ayni adli cizgi roman karakterine dayaniyor, ancak kendi ozgun hikayesini sunuyor.";
    char filmoyuncular4[1000]="Joaquin Phoenix, Robert De Niro, Zazie Beetz, Bryan Callen, Frances Conroy,Glenn Freshler";
    char filmseans4[200]="18.00-20.00, 19.30-21.30";
    int  koltuksayisi4=104;
    int  biletfiyati4=100;
    printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm Seans:%s\nFilm koltuk sayisi:%d\nBilet fiyati:%d",filmadi4,filmkonusu4,filmoyuncular4,filmseans4,koltuksayisi4,biletfiyati4);
     /*---------------FİLM 5-------------------*/
    printf("\n---------------FILM5---------------\n");
    char filmadi5[1000]="Minions";
    char filmkonusu5[1000]="Tarihin baslangicindan itibaren var olan ve kotu bir efendiye hizmet etmek isteyen sari renkli yaratiklar olan minionlarin maceralarini anlatiyor. Minionlar, dinozorlardan Firavun'a, Napolyon'dan Dracula'ya pek cok kisiye hizmet etmeye calissalar da, hepsinin sonu yok olmak olur. Sonunda Antarktika'ya yerlesip kendi kendilerine yasamaya baslarlar. Ancak 1960'li yillarda isler degisir, cunku uc kisilik maceraci bir minion ekibi, Amerika'ya dogru yola cikmaya karar verir. Orada, dunyanin ilk kadin super kotosu olan Scarlet Overkill'e hizmet etmeye calisirlar. Ancak bu da pek kolay olmayacaktir. Film, komedi ve animasyon turunde bir yapimdir.";
    char filmoyuncular5[1000]="Pierre Coffin, Chris Meledandri, Sandra Bullock, Jon Hamm";
    char filmseans5[200]="14.00-15.30, 16.00-17.30";
    int  koltuksayisi5=22;
    int  biletfiyati5=150;
    printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm Seans:%s\nFilm koltuk sayisi:%d\nBilet fiyati:%d",filmadi5,filmkonusu5,filmoyuncular5,filmseans5,koltuksayisi5,biletfiyati5);
}
#define MAXi_FILM_SAYISI 5
#define MAXi_SEANS_SAYISI 10
struct Filmnes {
    char ad[100];
    char filmkonusu[1000];
    char filmoyunculari[1000];
    char filmseans[100];
    //int koltuksayisi;
    int biletfiyati;
};

struct seanskoltuk
{
    int film1seans1;
    int film1seans2;
    int film2seans1;
    int film2seans2;
    int film3seans1;
    int film3seans2;
    int film4seans1;
    int film4seans2;
    int film5seans1;
    int film5seans2;
};

struct seanskoltuk seanskoltuksayilari[MAXi_SEANS_SAYISI]=
{
    {100,50,25,10,5,2,23,81,1,21}
};


struct Filmnes filmlernes[MAXi_FILM_SAYISI] = {
    {"Ex Machine","Bir internet sirketinin yazilim muhendisi olan Caleb Smith'in (Domhnall Gleeson), sirketin zeki ve gizemli CEO'su Nathan Bateman'in (Oscar Isaac) yarattigi yapay zeka robotu Ava'yi (Alicia Vikander) test etmesini anlatiyor. Caleb, Ava'yi test ederken, Ava'nin gercek bir bilinc sahibi olup olmadigina karar vermeye calisir. Bu arada, Nathan'in gizli planlari ve Ava'nin kendisine olan ilgisi Caleb'i zor durumda birakir. Film, yapay zeka, bilinc, insanlik ve etik gibi konulara deginmektedir.","Caleb Smith,Ava,Nathan Bateman,Kyoko,Jasmine,Amber","\n[1]13:00\n[2]16:00",75},
    {"Ataturk","Mustafa Kemal Ataturk'un hayatini, mucadelelerini ve Turkiye Cumhuriyeti'ni kurma surecini anlatiyor. Film, Ataturk'un cocukluk yillarindan baslayarak, milli mucadelenin lideri olmaya giden yolda yasadigi olaylari, insani yonlerini ve karsilastigi zorluklari gozler onune seriyor. Filmde, Ataturk'un annesi Zubeyde Hanim, babasi Ali Riza Efendi, arkadaslari Ali Fuat Cebesoy, Ismet Inonu, Kazim Karabekir, dusmanlari Enver Pasa, Otto Liman von Sanders, Stiliyan Kovacev gibi tarihi kisilikler de yer aliyor. Film, iki bolumden olusuyor.","Aras Bulut İynemli, Songül Öden, Mehmet Günsür, Sarp Akkaya, Berk Cankat, Esra Bilgiç, Sahra Şaş, Bertan Asllani, Beran Kotan, Hamdi Alkan, Emma Watson, Darko Peri","\n[1]13:00\n[2]16:00",50},
    {"Buz devri","Buzul caginin basladigi bir donemde, bir mamut, bir kilic disli kaplan, bir miskin ve bir insan bebegin maceralarini anlatiyor. Bu dort karakter, birlikte guneye dogru yol alirken, bir yandan sogukla, bir yandan da diger hayvanlarla mucadele ediyorlar. Film, komedi ve animasyon turunde bir yapimdir.","John Leguizamo, Ray Romano, Chris Wedge, Denis Leary, Jack Black, Alan Tudyk","\n[1]13:00\n[2]16:00",25},
    {"Joker","Gotham City'de yasayan basarisiz bir komedyen olan Arthur Fleck'in (Joaquin Phoenix), toplum tarafindan dislanmasina ve zorbaliga ugramasina dayanamayarak, psikolojik olarak cokuse gecmesini ve kendisini suc ve kaosun icinde bulan Joker karakterine donusmesini anlatiyor. Film, DC Comics'in ayni adli cizgi roman karakterine dayaniyor, ancak kendi ozgun hikayesini sunuyor.","Joaquin Phoenix, Robert De Niro, Zazie Beetz, Bryan Callen, Frances Conroy,Glenn Freshler","\n[1]13:00\n[2]16:00",100},
    {"Minions","Tarihin baslangicindan itibaren var olan ve kotu bir efendiye hizmet etmek isteyen sari renkli yaratiklar olan minionlarin maceralarini anlatiyor. Minionlar, dinozorlardan Firavun'a, Napolyon'dan Dracula'ya pek cok kisiye hizmet etmeye calissalar da, hepsinin sonu yok olmak olur. Sonunda Antarktika'ya yerlesip kendi kendilerine yasamaya baslarlar. Ancak 1960'li yillarda isler degisir, cunku uc kisilik maceraci bir minion ekibi, Amerika'ya dogru yola cikmaya karar verir. Orada, dunyanin ilk kadin super kotosu olan Scarlet Overkill'e hizmet etmeye calisirlar. Ancak bu da pek kolay olmayacaktir. Film, komedi ve animasyon turunde bir yapimdir.","Pierre Coffin, Chris Meledandri, Sandra Bullock, Jon Hamm","\n[1]13:00\n[2]16:00",150},
    // Diğer filmler
};
void bilgiler(struct Kullanici *kullanici, struct Filmnes *filmlernes, struct seanskoltuk *seanskoltuksayilari){
    system("cls");
    printf("*****BILGI EKRANI*****\n");
    printf("Seanslarim:\n%s",kullanici->biletlerim);
}
// Film bilgilerini içeren bir dizi tanımla
void Filmler(struct Kullanici *kullanici, struct Filmnes *filmlernes, struct seanskoltuk *seanskoltuksayilari) {
    int filmsec;
    int satinal;
    int seanssec;
    int seanssecsec;
    char exmachine1[1000]="Ex Machine Seans [13.00]\n";
    char exmachine2[1000]="Ex Machine Seans [16.00]\n";
    char ataturk1[1000]="Ataturk Seans [13.00]\n";
    char ataturk2[1000]="Ataturk Seans [16.00]\n";
    char buzdevri1[1000]="Buzdevri Seans [13.00]\n";
    char buzdevri2[1000]="Buzdevri Seans [16.00]\n";
    char joker1[1000]="Joker Seans [13.00]\n";
    char joker2[1000]="Joker Seans [16.00]\n";
    char minions1[1000]="Minions Seans [13.00]\n";
    char minions2[1000]="Minions Seans [16.00]\n";
    //int seans1=100;
    system("cls");
    printf("\n*****Filmler*****\n");
    printf("[1] %s\n", filmlernes[0].ad);
    printf("[2] %s\n", filmlernes[1].ad);
    printf("[3] %s\n", filmlernes[2].ad);
    printf("[4] %s\n", filmlernes[3].ad);
    printf("[5] %s\n", filmlernes[4].ad);
    printf("[0] Geri Gel\n");
    printf("Film Sec:");
    scanf("%d", &filmsec);
    system("cls");
    switch (filmsec){
    case 1:
            printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm seanslari:%s\n[3]Geri gel\nBilet fiyati:%d\n",filmlernes[0].ad,filmlernes[0].filmkonusu,filmlernes[0].filmoyunculari,filmlernes[0].filmseans,filmlernes[0].biletfiyati);
            printf("Seans sec:");
            scanf("%d",&seanssec);
            if(seanssec==1){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film1seans1);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[0].biletfiyati && seanskoltuksayilari[0].film1seans1>=1){
                    kullanici->bakiye -= filmlernes[0].biletfiyati;
                    seanskoltuksayilari[0].film1seans1--;
                    strcat(kullanici->biletlerim, exmachine1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye); break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");;break;
                }
            }
            if (seanssec==2){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film1seans2);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[0].biletfiyati && seanskoltuksayilari[0].film1seans2>=1){
                    kullanici->bakiye -= filmlernes[0].biletfiyati;
                   seanskoltuksayilari[0].film1seans2--;
                   strcat(kullanici->biletlerim, exmachine2);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye); break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");;break;
                }
            }
            if (seanssec==3){
                break;
            }
    
    case 2:printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm seanslari:%s\n[3]Geri gel\nBilet fiyati:%d\n",filmlernes[1].ad,filmlernes[1].filmkonusu,filmlernes[1].filmoyunculari,filmlernes[1].filmseans,filmlernes[1].biletfiyati);
            printf("seans sec:");
            scanf("%d",&seanssec);
            if(seanssec==1){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film2seans1);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[1].biletfiyati && seanskoltuksayilari[0].film2seans1>=1){
                    kullanici->bakiye -= filmlernes[1].biletfiyati;
                   seanskoltuksayilari[0].film2seans1--;
                   strcat(kullanici->biletlerim,ataturk1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    printf("yetersiz bakiye!!");
                }
            }
            if (seanssec==2){
                 printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film2seans2);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[1].biletfiyati && seanskoltuksayilari[0].film2seans2>=1){
                    kullanici->bakiye -= filmlernes[1].biletfiyati;
                   seanskoltuksayilari[0].film2seans2--;
                    strcat(kullanici->biletlerim,ataturk2);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye); break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            
            break;
    case 3:printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm seanslari:%s\n[3]Geri gel\nBilet fiyati:%d\n",filmlernes[2].ad,filmlernes[2].filmkonusu,filmlernes[2].filmoyunculari,filmlernes[2].filmseans,filmlernes[2].biletfiyati);
            printf("seans sec:");
            scanf("%d",&seanssec);
            if(seanssec==1){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[2].film3seans1);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[2].biletfiyati && seanskoltuksayilari[0].film3seans1>=1){
                    kullanici->bakiye -= filmlernes[2].biletfiyati;
                   seanskoltuksayilari[0].film3seans1--;
                    strcat(kullanici->biletlerim,buzdevri1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            if (seanssec==2){
                 printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film3seans2);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[2].biletfiyati && seanskoltuksayilari[0].film3seans2>=1){
                    kullanici->bakiye -= filmlernes[2].biletfiyati;
                   seanskoltuksayilari[0].film3seans2--;
                   strcat(kullanici->biletlerim,buzdevri2);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            
            break;
    case 4:printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm seanslari:%s\n[3]Geri gel\nBilet fiyati:%d\n",filmlernes[3].ad,filmlernes[3].filmkonusu,filmlernes[3].filmoyunculari,filmlernes[3].filmseans,filmlernes[3].biletfiyati);
            printf("seans sec:");
            scanf("%d",&seanssec);
            if(seanssec==1){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film4seans1);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[3].biletfiyati && seanskoltuksayilari[0].film4seans1>=1){
                    kullanici->bakiye -= filmlernes[3].biletfiyati;
                   seanskoltuksayilari[0].film4seans1--;
                   strcat(kullanici->biletlerim,joker1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            if (seanssec==2){
                 printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film4seans2);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[3].biletfiyati && seanskoltuksayilari[0].film4seans2>=1){
                    kullanici->bakiye -= filmlernes[3].biletfiyati;
                   seanskoltuksayilari[0].film4seans2--;
                   strcat(kullanici->biletlerim,joker2);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            
            break;
    case 5:printf("Film adi:%s\nFilm konusu:%s\nFilm oyunculari:%s\nFilm seanslari:%s\n[3]Geri gel\nBilet fiyati:%d\n",filmlernes[4].ad,filmlernes[4].filmkonusu,filmlernes[4].filmoyunculari,filmlernes[4].filmseans,filmlernes[4].biletfiyati);
            printf("seans sec:");
            scanf("%d",&seanssec);
            if(seanssec==1){
                printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film5seans1);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[4].biletfiyati && seanskoltuksayilari[0].film5seans1>=1){
                    kullanici->bakiye -= filmlernes[4].biletfiyati;
                   seanskoltuksayilari[0].film5seans1--;
                   strcat(kullanici->biletlerim,minions1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");;break;
                }
            }
            if (seanssec==2){
                 printf("Koltuk sayisi:%d\n",seanskoltuksayilari[0].film5seans2);
                printf("[1]Satin al\n[2]Geri gel\n");
                printf("Islem sec:");
                scanf("%d",&seanssecsec);
                if(seanssecsec==1 && kullanici->bakiye >= filmlernes[4].biletfiyati && seanskoltuksayilari[0].film5seans2>=1){
                    kullanici->bakiye -= filmlernes[4].biletfiyati;
                   seanskoltuksayilari[0].film5seans2--;
                   strcat(kullanici->biletlerim,minions1);
                    printf("Kalan bakiye:%.2f\n", kullanici->bakiye);break;
                }
                else if(seanssecsec==2){
                    break;
                }
                else {
                    system("cls");printf("Yetersiz bakiye!!");break;
                }
            }
            
            break;        
    default:
        break;
    }
}
/*--------------------------YORUMLAR-------------------------------------*/
#define MAX_YORUM_UZUNLUK 1000
#define MAX_FILM_ADI_UZUNLUK 50
#define MAX_FILM_SAYISI 5

// Yorum yapısını tanımla
struct Yorum {
    char yorum[MAX_YORUM_UZUNLUK];
    struct Yorum *sonraki;
};

// Film yapısını tanımla
struct Film {
    char ad[MAX_FILM_ADI_UZUNLUK];
    struct Yorum *yorumlar;
};

// Yorum ekleyen fonksiyonu tanımla
void yorumEkle(struct Film *film, const char *yorum) {
    struct Yorum *yeniYorum = (struct Yorum *)malloc(sizeof(struct Yorum));//malloc dijital bellek, ihtiyac sağlanmazsa NULL DÖNER!
    if (yeniYorum == NULL) {
        printf("Bellek tahsisi hatasi.\n");
        exit(1);
    }

    snprintf(yeniYorum->yorum, MAX_YORUM_UZUNLUK, "%s", yorum);

    yeniYorum->sonraki = film->yorumlar;
    film->yorumlar = yeniYorum;
}

// Yorumları görüntüleyen fonksiyonu tanımla
void yorumlariGoruntule(struct Film *film) {
    struct Yorum *gezici = film->yorumlar;

    printf("*****Yorumlar*****\n");
    while (gezici != NULL) {
        printf("- %s\n", gezici->yorum);
        gezici = gezici->sonraki;
    }
}
int YorumYap() {
    struct Film filmler[MAX_FILM_SAYISI] = {
        {"Ex Machine", NULL},
        {"Ataturk", NULL},
        {"Buz Devri", NULL},
        {"Joker", NULL},
        {"Minions", NULL}
    };

    int secim;
    while (1) {
        printf("1. Ex Machine\n");
        printf("2. Ataturk\n");
        printf("3. Buz Devri\n");
        printf("4. Joker\n");
        printf("5. Minions\n");
        printf("0. Geri gel\n");
        printf("Secim yap:");

        scanf("%d", &secim);

        if (secim == 0) {
            break;
        } else if (secim < 1 || secim > MAX_FILM_SAYISI) {
            printf("Gecersiz secim. Lutfen tekrar deneyin.\n");
        } else {
            printf("%s'i sectiniz. Ne yapmak istersiniz?\n", filmler[secim - 1].ad);
            printf("1. Yorum yap\n");
            printf("2. Yorumlari goruntule\n");

            int islem;
            scanf("%d", &islem);

            if (islem == 1) {
                printf("Yorumunuzu giriniz:\n");
                char yorum[MAX_YORUM_UZUNLUK];
                scanf(" %[^\n]s", yorum);
                yorumEkle(&filmler[secim - 1], yorum);
                printf("Yorumunuz basariyla kaydedildi.\n");
            } else if (islem == 2) {
                yorumlariGoruntule(&filmler[secim - 1]);
            } else {
                printf("Gecersiz secim. Lutfen tekrar deneyin.\n");
            }
        }
    }

    return 0;
}
// Ana Menü
int menu() {
    int secim;
    printf("\n****ISLEMLER****\n");
    printf("[1] Bakiye Yukle\n");
    printf("[2] Filmler\n");
    printf("[3] Yorum Yap\n");
    printf("[4] Seanslarim\n");
    printf("[5] Kayit Ekranina Don\n");
    printf("[0] Cikis Yap\n");
    printf("Islem sec:");
    scanf("%d", &secim);
    return secim;
}

int main() {
     struct Kullanici kullanicilar[MAX_KULLANICI];
    int kullaniciSayisi = 0;
    int secim;
    
    FILE *dosya = fopen("kullanicilar.txt", "r");
    if (dosya == NULL) {
        printf("Dosya acilamadi.\n");
        return -1;
    }

    while (fscanf(dosya, "%s %s %f", kullanicilar[kullaniciSayisi].kullaniciAdi,
                  kullanicilar[kullaniciSayisi].sifre, &kullanicilar[kullaniciSayisi].bakiye) == 3) {
        
        // bakiyeden sonraki tüm verileri biletler olarak okuması için ayrı yazıldı!
        fscanf(dosya, "%[^\n]", kullanicilar[kullaniciSayisi].biletlerim);

        kullaniciSayisi++;
        if (kullaniciSayisi >= MAX_KULLANICI) {
            printf("Maksimum kullanici sayisina ulasildi.\n");
            break;
        }
    }

    fclose(dosya);


    printf("*******************************\n");
    printf("*                             *\n");
    printf("*         HOSGELDIN!          *\n");
    printf("*                             *\n");
    printf("*******************************\n");
    sleep(2);
    // system("cls");
    do {
        printf("\n1. Kayit Ol\n");
        printf("2. Giris Yap\n");
        printf("3. Filmler\n");
        printf("0. Cikis\n");
        printf("Seciminizi yapin: ");
        scanf("%d", &secim);
        system("cls");

        switch (secim) {
        case 1:
            // Kayıt olma
            kayitOl(kullanicilar, &kullaniciSayisi);
            break;
        case 2: {
            // Giriş yapma
            int kullaniciIndeks = girisYap(kullanicilar, kullaniciSayisi);
            if (kullaniciIndeks != -1) {
                // Giriş başarılı, menüye git
                int menuSecim;
                do {
                    menuSecim = menu();
                    switch (menuSecim) {
                    case 1:
                        BakiyeYukle(kullanicilar, kullaniciIndeks);
                        break;
                    case 2:
                         Filmler(&kullanicilar[kullaniciIndeks], filmlernes, seanskoltuksayilari);
                        break;
                    case 3:
                        YorumYap();system("cls");
                        break;
                    case 4:bilgiler(&kullanicilar[kullaniciIndeks], filmlernes, seanskoltuksayilari);break;
                    case 5:
                        system("cls"); printf("Kayit Ekranina Yonlendiriliyor...\n"); 
                        break;
                    case 0:
                    dosyayaKullaniciBilgileriniKaydet(kullanicilar, kullaniciIndeks);
                    system("cls");printf("*******************************\n");
                                         printf("*                             *\n");
                                         printf("*          HOSCAKAL!          *\n");
                                         printf("*                             *\n");
                                         printf("*******************************\n");
                                        sleep(2);return 0;
                    default:
                        printf("Gecersiz secim! Lutfen tekrar deneyin.\n");
                        break;
                    }
                } while (menuSecim != 5);
            }
        } break;
        case 0:
                                         printf("*******************************\n");
                                         printf("*                             *\n");
                                         printf("*          HOSCAKAL!          *\n");
                                         printf("*                             *\n");
                                         printf("*******************************\n");
                                         sleep(2);
            break;
        case 3:filmlist();break;
        default:
            printf("Gecersiz secim! Lutfen tekrar deneyin.\n");
            break;
        }
    } while (secim != 0);

    return 0;
}



