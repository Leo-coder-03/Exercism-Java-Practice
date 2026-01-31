import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class Ledger {

    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        LedgerEntry e = new LedgerEntry();
        e.setLocalDate(LocalDate.parse(date));
        e.setDescription(description);
        e.setChange(change);
        return e;
    }

    public String format(String currency, String locale, LedgerEntry[] entries) {
        Config cfg = Config.of(currency, locale);
        if (entries.length == 0) return cfg.header;

        return cfg.header + "\n" +
                Arrays.stream(entries)
                        .sorted(
                                Comparator.comparing(LedgerEntry::getLocalDate)
                                        .thenComparing(LedgerEntry::getDescription)
                                        .thenComparing(LedgerEntry::getChange)
                        )
                        .map(e -> row(e, cfg))
                        .collect(Collectors.joining("\n"));
    }

    private String row(LedgerEntry e, Config cfg) {
        String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(cfg.datePattern));
        String desc = e.getDescription().length() <= 25
                ? e.getDescription()
                : e.getDescription().substring(0, 22) + "...";
        String amount = amount(e.getChange(), cfg);
        return String.format("%s | %-25s | %13s", date, desc, amount);
    }

    private String amount(double change, Config cfg) {
        boolean neg = change < 0;
        double v = Math.abs(change) / 100.0;

        String[] p = String.format(Locale.US, "%.2f", v).split("\\.");
        String intp = p[0];
        String dec = p[1];

        StringBuilder g = new StringBuilder();
        for (int i = 0; i < intp.length(); i++) {
            if (i > 0 && (intp.length() - i) % 3 == 0) g.append(cfg.thousand);
            g.append(intp.charAt(i));
        }

        String base = g + cfg.decimal + dec;

        if (neg && cfg.locale.equals("en-US"))
            return "(" + cfg.symbol + cfg.space + base + ")";

        if (neg && cfg.locale.equals("nl-NL"))
            return " " + cfg.symbol + " -" + base + " ";

        if (cfg.locale.equals("nl-NL"))
            return " " + cfg.symbol + cfg.space + base + " ";

        return cfg.symbol + cfg.space + base + " ";
    }

    private static class Config {
        final String symbol;
        final String datePattern;
        final String decimal;
        final String thousand;
        final String header;
        final String locale;
        final String space;

        Config(String s, String d, String dec, String th, String h, String l, String sp) {
            symbol = s;
            datePattern = d;
            decimal = dec;
            thousand = th;
            header = h;
            locale = l;
            space = sp;
        }

        static Config of(String currency, String locale) {
            if (!currency.equals("USD") && !currency.equals("EUR"))
                throw new IllegalArgumentException("Invalid currency");
            if (!locale.equals("en-US") && !locale.equals("nl-NL"))
                throw new IllegalArgumentException("Invalid locale");

            String s = currency.equals("USD") ? "$" : "€";

            if (locale.equals("en-US"))
                return new Config(
                        s,
                        "MM/dd/yyyy",
                        ".",
                        ",",
                        "Date       | Description               | Change       ",
                        locale,
                        ""
                );

            return new Config(
                    s,
                    "dd/MM/yyyy",
                    ",",
                    ".",
                    "Datum      | Omschrijving              | Verandering  ",
                    locale,
                    " "
            );
        }
    }

    public static class LedgerEntry {
        LocalDate localDate;
        String description;
        double change;

        public LocalDate getLocalDate() { return localDate; }
        public void setLocalDate(LocalDate localDate) { this.localDate = localDate; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public double getChange() { return change; }
        public void setChange(double change) { this.change = change; }
    }
}
