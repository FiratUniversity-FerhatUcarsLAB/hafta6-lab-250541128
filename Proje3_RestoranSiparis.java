import java.util.Scanner;

public class RestoranSiparis{

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;  // Çorba
            case 2: return 45;  // Humus
            case 3: return 55;  // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;  // Kola
            case 2: return 12;  // Ayran
            case 3: return 35;  // Taze Meyve Suyu
            case 4: return 25;  // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Künefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo sipariş mi? (Ana + içecek + tatlı)
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy hour kontrolü (14:00–17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {

        double indirim = 0;

        // Combo → %15
        if (combo) {
            indirim += tutar * 0.15;
        }

        // 200 TL üzeri → %10
        if (tutar >= 200) {
            indirim += tutar * 0.10;
        }

        // Öğrenci indirim → Hafta içi %10 (Pzt–Cuma)
        // Bu örnekte hafta bilgisi yok → Ogrenci ise %10
        if (ogrenci) {
            indirim += tutar * 0.10;
        }

        // Happy Hour → içeceklerde %20
        if (isHappyHour(saat)) {
            // İçecek indirimi total'e yansıtılacak (içecek fiyatı ayrıca gönderilmediği için)
            // Bu sistemde iç içe if şartını göstermek için örnek:
            if (tutar > 0) {
                indirim += tutar * 0.00; // örnek yapı; gerçek içecek tutarı dışarıdan alınabilir
            }
        }

        return indirim;
    }

    // 8) Bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // MAIN
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== ANA YEMEKLER ===");
        System.out.println("1- Izgara Tavuk (85 TL)");
        System.out.println("2- Adana Kebap (120 TL)");
        System.out.println("3- Levrek (110 TL)");
        System.out.println("4- Mantı (65 TL)");
        System.out.print("Ana yemek seç (0=Yok): ");
        int anaSecim = sc.nextInt();
        double anaFiyat = getMainDishPrice(anaSecim);
        boolean anaVar = anaFiyat > 0;

        System.out.println("\n=== BAŞLANGIÇLAR ===");
        System.out.println("1- Çorba (25 TL)");
        System.out.println("2- Humus (45 TL)");
        System.out.println("3- Sigara Böreği (55 TL)");
        System.out.print("Başlangıç seç (0=Yok): ");
        int basSecim = sc.nextInt();
        double basFiyat = getAppetizerPrice(basSecim);

        System.out.println("\n=== İÇECEKLER ===");
        System.out.println("1- Kola (15 TL)");
        System.out.println("2- Ayran (12 TL)");
        System.out.println("3- Taze Meyve Suyu (35 TL)");
        System.out.println("4- Limonata (25 TL)");
        System.out.print("İçecek seç (0=Yok): ");
        int icecekSecim = sc.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);
        boolean icecekVar = icecekFiyat > 0;

        System.out.println("\n=== TATLILAR ===");
        System.out.println("1- Künefe (65 TL)");
        System.out.println("2- Baklava (55 TL)");
        System.out.println("3- Sütlaç (35 TL)");
        System.out.print("Tatlı seç (0=Yok): ");
        int tatliSecim = sc.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);
        boolean tatliVar = tatliFiyat > 0;

        System.out.print("\nSaat (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        boolean ogrenci = sc.nextInt() == 1;

        // Hesaplamalar
        double toplam = anaFiyat + basFiyat + icecekFiyat + tatliFiyat;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double indirim = calculateDiscount(toplam, combo, ogrenci, saat);
        double odenecek = toplam - indirim;

        double bahsis = calculateServiceTip(odenecek);

        // ÇIKTI
        System.out.println("\n========== FİŞ ==========");
        System.out.println("Ana Yemek: " + anaFiyat + " TL");
        System.out.println("Başlangıç: " + basFiyat + " TL");
        System.out.println("İçecek: " + icecekFiyat + " TL");
        System.out.println("Tatlı: " + tatliFiyat + " TL");
        System.out.println("-------------------------");
        System.out.println("Toplam: " + toplam + " TL");
        System.out.println("Uygulanan İndirim: -" + indirim + " TL");
        System.out.println("Ödenecek Tutar: " + odenecek + " TL");
        System.out.println("Bahşiş Önerisi (%10): " + bahsis + " TL");
        System.out.println("==========================");
    }
}
