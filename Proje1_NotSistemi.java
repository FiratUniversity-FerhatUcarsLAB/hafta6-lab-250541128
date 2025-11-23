import java.util.Scanner;

public class NotSistemi{

    // Ortalama hesaplama metodu
    public static double calculateAverage(double vize, double finalNotu, double odev) {
        return vize * 0.30 + finalNotu * 0.40 + odev * 0.30;
    }

    // Geçme durumu (ortalama ≥ 50)
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // Harf notu belirleme
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) return "A";
        else if (ortalama >= 80) return "B";
        else if (ortalama >= 70) return "C";
        else if (ortalama >= 60) return "D";
        else return "F";
    }

    // Onur listesi durumu
    // Ortalama ≥ 85 VE hiçbir not < 70 olmamalı
    public static boolean isHonorList(double ortalama, double vize, double finalNotu, double odev) {
        return ortalama >= 85 && vize >= 70 && finalNotu >= 70 && odev >= 70;
    }

    // Bütünleme hakkı: 40 ≤ ort < 50
    public static boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40 && ortalama < 50;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Vize notunu giriniz (0-100): ");
        double vize = input.nextDouble();

        System.out.print("Final notunu giriniz (0-100): ");
        double finalNotu = input.nextDouble();

        System.out.print("Odev notunu giriniz (0-100): ");
        double odev = input.nextDouble();

        double ortalama = calculateAverage(vize, finalNotu, odev);

        System.out.println("\n==== DETAYLI NOT RAPORU ====");
        System.out.printf("Ortalama: %.2f\n", ortalama);

        // Geçme / Kalma
        if (isPassingGrade(ortalama))
            System.out.println("Geçme Durumu: GEÇTİ");
        else
            System.out.println("Geçme Durumu: KALDI");

        // Harf Notu
        System.out.println("Harf Notu: " + getLetterGrade(ortalama));

        // Onur Listesi
        if (isHonorList(ortalama, vize, finalNotu, odev))
            System.out.println("Onur Listesi: EVET");
        else
            System.out.println("Onur Listesi: HAYIR");

        // Bütünleme hakkı
        if (hasRetakeRight(ortalama))
            System.out.println("Bütünleme Hakkı: VAR");
        else
            System.out.println("Bütünleme Hakkı: YOK");
    }
}

