import java.util.Scanner;

public class SinemaBileti {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7; // Cumartesi - Pazar
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;      // Hafta içi matine
        if (!weekend && !matinee) return 65;     // Hafta içi normal
        if (weekend && matinee) return 55;       // Hafta sonu matine
        return 85;                               // Hafta sonu normal
    }

    // 4) İndirim miktarı hesaplama
    // öğrenci(1), öğretmen(2), diğer(3)
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // 65+ yaş → %30
        if (yas >= 65) return 0.30;

        // 12 yaş altı → %25
        if (yas < 12) return 0.25;

        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) return 0.20;  // Pzt–Perş
                else return 0.15;                       // Cuma–Pazar

            case 2: // Öğretmen
                if (gun == 3) return 0.35;              // Çarşamba özel
                break;
        }

        return 0.0; //Diğer
    }

    // 5) Format ekstra ücreti
    public static int getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Son fiyat hesaplama
    public static double calculateFinalPrice(double base, double discountRate, int extra) {
        double indirimli = base - (base * discountRate);
        return indirimli + extra;
    }

    // 7) Bilet bilgisi oluşturma
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru,
                                            double base, double discountRate, int extra, double finalPrice) {

        String gunAdi = switch (gun) {
            case 1 -> "Pazartesi";
            case 2 -> "Salı";
            case 3 -> "Çarşamba";
            case 4 -> "Perşembe";
            case 5 -> "Cuma";
            case 6 -> "Cumartesi";
            case 7 -> "Pazar";
            default -> "Bilinmiyor";
        };

        String meslekAdi = switch (meslek) {
            case 1 -> "Öğrenci";
            case 2 -> "Öğretmen";
            default -> "Diğer";
        };

        String formatAdi = switch (filmTuru) {
            case 1 -> "2D";
            case 2 -> "3D";
            case 3 -> "IMAX";
            case 4 -> "4DX";
            default -> "Bilinmiyor";
        };

        return "\n==== SİNEMA BİLETİ ====\n"
                + "Gün: " + gunAdi + "\n"
                + "Saat: " + saat + ":00\n"
                + "Yaş: " + yas + "\n"
                + "Meslek: " + meslekAdi + "\n"
                + "Film Türü: " + formatAdi + "\n"
                + "Temel Fiyat: " + base + " TL\n"
                + "İndirim Oranı: %" + (int)(discountRate * 100) + "\n"
                + "Format Ekstra: +" + extra + " TL\n"
                + "----------------------\n"
                + "TOPLAM FİYAT: " + finalPrice + " TL\n";
    }

    // MAIN
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1=Pzt, 2=Salı, 3=Çarş., 4=Perş., 5=Cuma, 6=Cts, 7=Pazar");
        System.out.print("Gün: ");
        int gun = sc.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        System.out.println("Meslek: 1=Öğrenci, 2=Öğretmen, 3=Diğer");
        System.out.print("Seçim: ");
        int meslek = sc.nextInt();

        System.out.println("Film Türü: 1=2D, 2=3D, 3=IMAX, 4=4DX");
        System.out.print("Seçim: ");
        int filmTuru = sc.nextInt();

        // Hesaplamalar
        double base = calculateBasePrice(gun, saat);
        double discount = calculateDiscount(yas, meslek, gun);
        int extra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(base, discount, extra);

        // Bilgi Yazdırma
        System.out.println(
                generateTicketInfo(gun, saat, yas, meslek, filmTuru,
                        base, discount, extra, finalPrice));
    }
}

