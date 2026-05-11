

import java.util.ArrayList;
import java.util.Random;

//KİSİ SINIFI BURDA BASLAMAKTA

class Kisi {

    private String ad;
    private String soyad;
    private String email;
    private int telefonNumarasi;

    public Kisi(String ad, String soyad,
                String email, int telefonNumarasi) {

        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
    }

    // getter ve setter metodlarım
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(int telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    
    public String toString() {

        return "Ad: " + ad +
                "\nSoyad: " + soyad +
                "\nEmail: " + email +
                "\nTelefon: " + telefonNumarasi;
    }
}

// Bankaya ait personelin sınıfı.

class BankaPersoneli extends Kisi {

    private String personelID;
    private ArrayList<Musteri> musteriler;

    public BankaPersoneli(String ad,
                          String soyad,
                          String email,
                          int telefonNumarasi) {

        super(ad, soyad, email, telefonNumarasi);

        Random random = new Random();

        this.personelID =
                "PER" + (1000 + random.nextInt(9000));

        musteriler = new ArrayList<>();
    }

    public void musteriEkle(Musteri musteri) {
        musteriler.add(musteri);
    }

    public String getPersonelID() {
        return personelID;
    }

    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    @Override
    public String toString() {

        return super.toString() +
                "\nPersonel ID: " + personelID;
    }
}

// Kullanıcının Banka hesabı Sınıfı

class BankaHesabi {

    private String iban;
    protected double bakiye;

    public BankaHesabi(double bakiye) {

        Random random = new Random();

        this.iban =
                "TR" + (100000 + random.nextInt(900000));

        this.bakiye = bakiye;
    }

    public String getIban() {
        return iban;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    @Override
    public String toString() {

        return "IBAN: " + iban +
                "\nBakiye: " + bakiye;
    }
}

// kredi kartı sınıfı

class KrediKarti {

    private String kartNumarasi;
    private double limit;
    private double guncelBorc;
    private double kullanilabilirLimit;

    public KrediKarti(double limit,
                      double guncelBorc) {

        Random random = new Random();

        this.kartNumarasi =
                "5400" + (100000 + random.nextInt(900000));

        this.limit = limit;
        this.guncelBorc = guncelBorc;

        this.kullanilabilirLimit =
                limit - guncelBorc;
    }

    public String getKartNumarasi() {
        return kartNumarasi;
    }

    public double getLimit() {
        return limit;
    }

    public double getGuncelBorc() {
        return guncelBorc;
    }

    public void setGuncelBorc(double guncelBorc) {
        this.guncelBorc = guncelBorc;
    }

    @Override
    public String toString() {

        return "Kart No: " + kartNumarasi +
                "\nLimit: " + limit +
                "\nGüncel Borç: " + guncelBorc +
                "\nKullanılabilir Limit: " +
                kullanilabilirLimit;
    }
}

// vadesiz hesap sınfı

class VadesizHesap extends BankaHesabi {

    private String hesapTuru;

    public VadesizHesap(double bakiye) {

        super(bakiye);

        hesapTuru = "Vadesiz";
    }

    // PARA TRANSFERI
    public void paraTransferi(BankaHesabi aliciHesap,
                              BankaHesabi gonderenHesap,
                              double miktar) {

        if (gonderenHesap.getBakiye() >= miktar) {

            gonderenHesap.setBakiye(
                    gonderenHesap.getBakiye() - miktar
            );

            aliciHesap.setBakiye(
                    aliciHesap.getBakiye() + miktar
            );

            System.out.println(
                    miktar +
                    " TL transfer edildi."
            );

        } else {

            System.out.println(
                    "Yetersiz bakiye."
            );
        }
    }

    // KREDI KARTI BORCU ODEME
    public void krediKartiBorcOdeme(
            KrediKarti kart,
            double miktar) {

        if (this.bakiye >= miktar) {

            this.bakiye -= miktar;

            kart.setGuncelBorc(
                    kart.getGuncelBorc() - miktar
            );

            System.out.println(
                    "Kredi kartı borcu ödendi."
            );

        } else {

            System.out.println(
                    "Yetersiz bakiye."
            );
        }
    }

    
    public String toString() {

        return super.toString() +
                "\nHesap Türü: " + hesapTuru;
    }
}

// yatırım hesabı sınıfı 

class YatirimHesabi extends BankaHesabi {

    private String hesapTuru;

    public YatirimHesabi(double bakiye) {

        super(bakiye);

        hesapTuru = "Yatırım";
    }

    // PARA EKLE
    public void paraEkle(double miktar) {

        bakiye += miktar;

        System.out.println(
                miktar + " TL yatırıldı."
        );
    }

    // PARA CEK
    public void paraCek(double miktar) {

        if (bakiye >= miktar) {

            bakiye -= miktar;

            System.out.println(
                    miktar + " TL çekildi."
            );

        } else {

            System.out.println(
                    "Yetersiz bakiye."
            );
        }
    }

    
    public String toString() {

        return super.toString() +
                "\nHesap Türü: " + hesapTuru;
    }
}

// musteri sınıfı

class Musteri extends Kisi {

    private String musteriNumarasi;

    private ArrayList<BankaHesabi> hesaplar;

    private ArrayList<KrediKarti> krediKartlari;

    public Musteri(String ad,
                   String soyad,
                   String email,
                   int telefonNumarasi) {

        super(ad, soyad, email, telefonNumarasi);

        Random random = new Random();

        this.musteriNumarasi =
                "MUS" + (1000 + random.nextInt(9000));

        hesaplar = new ArrayList<>();

        krediKartlari = new ArrayList<>();
    }

    // HESAP EKLE
    public void hesapEkle(String hesapTuru,
                          double bakiye) {

        if (hesapTuru.equalsIgnoreCase("Vadesiz")) {

            VadesizHesap hesap =
                    new VadesizHesap(bakiye);

            hesaplar.add(hesap);

            System.out.println(
                    "Vadesiz hesap oluşturuldu."
            );

        } else if (
                hesapTuru.equalsIgnoreCase("Yatirim")) {

            YatirimHesabi hesap =
                    new YatirimHesabi(bakiye);

            hesaplar.add(hesap);

            System.out.println(
                    "Yatırım hesabı oluşturuldu."
            );

        } else {

            System.out.println(
                    "Geçersiz hesap türü."
            );
        }
    }

    // KREDI KARTI EKLE
    public void krediKartiEkle(double limit) {

        KrediKarti kart =
                new KrediKarti(limit, 0);

        krediKartlari.add(kart);

        System.out.println(
                "Kredi kartı oluşturuldu."
        );
    }

    // HESAP SIL
    public void hesapSil(BankaHesabi hesap) {

        if (hesap.getBakiye() > 0) {

            System.out.println(
                    "Lütfen öncelikle bakiyenizi başka bir hesaba aktarınız."
            );

        } else {

            hesaplar.remove(hesap);

            System.out.println(
                    "Hesap silindi."
            );
        }
    }

    // KREDI KARTI SIL
    public void krediKartiSil(KrediKarti kart) {

        if (kart.getGuncelBorc() == 0) {

            krediKartlari.remove(kart);

            System.out.println(
                    "Kart silindi."
            );

        } else {

            System.out.println(
                    "Lütfen öncelikle borç ödemesi yapınız."
            );
        }
    }

    public ArrayList<BankaHesabi> getHesaplar() {
        return hesaplar;
    }

    public ArrayList<KrediKarti> getKrediKartlari() {
        return krediKartlari;
    }

    @Override
    public String toString() {

        return super.toString() +
                "\nMüşteri No: " +
                musteriNumarasi;
    }
}



public class Main {

    public static void main(String[] args) {

        

        Musteri musteri1 =
                new Musteri(
                        "Ramazan Eren",
                        "Güzel",
                        "ramoerengzl2@gmail.com",
                        5551234
                );

        Musteri musteri2 =
                new Musteri(
                        "Mehmet",
                        "Demir",
                        "mehmet@gmail.com",
                        5555678
                );

        System.out.println(
                "Müşteriler oluşturuldu."
        );

        System.out.println(
                "--------------------------------"
        );

      
        // HESAP ACMA
        

        musteri1.hesapEkle(
                "Vadesiz",
                10000
        );

        musteri2.hesapEkle(
                "Yatirim",
                5000
        );

        System.out.println(
                "--------------------------------"
        );

        
        // HESAPLARI ALMA
       

        VadesizHesap hesap1 =
                (VadesizHesap)
                        musteri1.getHesaplar().get(0);

        YatirimHesabi hesap2 =
                (YatirimHesabi)
                        musteri2.getHesaplar().get(0);

       
        // PARA YATIRMA
        

        hesap2.paraEkle(3000);

        System.out.println(
                "--------------------------------"
        );

       
        // PARA TRANSFERI
        

        hesap1.paraTransferi(
                hesap2,
                hesap1,
                2000
        );

        System.out.println(
                "--------------------------------"
        );

        
        // KREDI KARTI OLUSTURMA
        
        musteri1.krediKartiEkle(15000);

        KrediKarti kart =
                musteri1.getKrediKartlari().get(0);

        // KARTA BORC EKLEME

        kart.setGuncelBorc(4000);

       
        // BORC ODEME
       

        hesap1.krediKartiBorcOdeme(
                kart,
                2000
        );

        System.out.println(
                "--------------------------------"
        );

        
        // HESAP SILME
      

        musteri1.hesapSil(hesap1);

        // BAKIYEYI SIFIRLAMA

        hesap1.setBakiye(0);

        musteri1.hesapSil(hesap1);

        System.out.println(
                "--------------------------------"
        );

       
        // KART SILME
       

        musteri1.krediKartiSil(kart);

        // BORCU SIFIRLAMA

        kart.setGuncelBorc(0);

        musteri1.krediKartiSil(kart);

        System.out.println(
                "--------------------------------"
        );

        // BILGILERI YAZDIRMA
      

        System.out.println(musteri1);

        System.out.println(
                "--------------------------------"
        );

        System.out.println(hesap2);

        System.out.println(
                "--------------------------------"
        );

        System.out.println(kart);
    }
}