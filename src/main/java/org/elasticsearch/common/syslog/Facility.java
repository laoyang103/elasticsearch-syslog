
package org.elasticsearch.common.syslog;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Syslog facility as defined in <a href="https://tools.ietf.org/html/rfc5424">RFC 5424 - The Syslog Protocol</a>.
 * <p/>
 * See <a href="http://tools.ietf.org/html/rfc5427">RFC 5427 - Textual Conventions for Syslog Management</a> for the {@link #label}.
 *
 */
public enum Facility implements Comparable<Facility> {

    /**
     * Kernel messages, numerical code 0.
     */
    KERN(0, "KERN"),
    /**
     * User-level messages, numerical code 1.
     */
    USER(1, "USER"),
    /**
     * Mail system, numerical code 2.
     */
    MAIL(2, "MAIL"),
    /**
     * System daemons, numerical code 3.
     */
    DAEMON(3, "DAEMON"),
    /**
     * Security/authorization messages, numerical code 4.
     */
    AUTH(4, "AUTH"),
    /**
     * Messages generated internally by syslogd, numerical code 5.
     */
    SYSLOG(5, "SYSLOG"),
    /**
     * Line printer subsystem, numerical code 6.
     */
    LPR(6, "LPR"),
    /**
     * Network news subsystem, numerical code 7.
     */
    NEWS(7, "NEWS"),
    /**
     * UUCP subsystem, numerical code 8
     */
    UUCP(8, "UUCP"),
    /**
     * Clock daemon, numerical code 9.
     */
    CRON(9, "CRON"),
    /**
     * Security/authorization  messages, numerical code 10.
     */
    AUTHPRIV(10, "AUTHPRIV"),
    /**
     * FTP daemon, numerical code 11.
     */
    FTP(11, "FTP"),
    /**
     * NTP subsystem, numerical code 12.
     */
    NTP(12, "NTP"),
    /**
     * Log audit, numerical code 13.
     */
    AUDIT(13, "AUDIT"),
    /**
     * Log alert, numerical code 14.
     */
    ALERT(14, "ALERT"),
    /**
     * Clock daemon, numerical code 15.
     */
    CLOCK(15, "CLOCK"),
    /**
     * Reserved for local use, numerical code 16.
     */
    LOCAL0(16, "LOCAL0"),
    /**
     * Reserved for local use, numerical code 17.
     */
    LOCAL1(17, "LOCAL1"),
    /**
     * Reserved for local use, numerical code 18.
     */
    LOCAL2(18, "LOCAL2"),
    /**
     * Reserved for local use, numerical code 19.
     */
    LOCAL3(19, "LOCAL3"),
    /**
     * Reserved for local use, numerical code 20.
     */
    LOCAL4(20, "LOCAL4"),
    /**
     * Reserved for local use, numerical code 21.
     */
    LOCAL5(21, "LOCAL5"),
    /**
     * Reserved for local use, numerical code 22.
     */
    LOCAL6(22, "LOCAL6"),
    /**
     * Reserved for local use, numerical code 23.
     */
    LOCAL7(23, "LOCAL7");

    private final static Map<String, Facility> facilityFromLabel = new HashMap<String, Facility>();

    private final static Map<Integer, Facility> facilityFromNumericalCode = new HashMap<Integer, Facility>();

    static {
        for (Facility facility : Facility.values()) {
            facilityFromLabel.put(facility.label, facility);
            facilityFromNumericalCode.put(facility.numericalCode, facility);
        }
    }

    /**
     * Syslog facility numerical code
     */
    private final int numericalCode;
    /**
     * Syslog facility textual code. Not {@code null}
     */
    private final String label;

    private Facility(int numericalCode, String label) {
        this.numericalCode = numericalCode;
        this.label = label;
    }

    /**
     * @param numericalCode Syslog facility numerical code
     * @return Syslog facility, not {@code null}
     * @throws IllegalArgumentException the given numericalCode is not a valid Syslog facility numerical code
     */
    public static Facility fromNumericalCode(int numericalCode) throws IllegalArgumentException {
        Facility facility = facilityFromNumericalCode.get(numericalCode);
        if (facility == null) {
            throw new IllegalArgumentException("Invalid facility '" + numericalCode + "'");
        }
        return facility;
    }

    /**
     * @param label Syslog facility textual code. {@code null} or empty returns {@code null}
     * @return Syslog facility, {@code null} if given value is {@code null}
     * @throws IllegalArgumentException the given value is not a valid Syslog facility textual code
     */
    public static Facility fromLabel(String label) throws IllegalArgumentException {
        if (label == null || label.isEmpty())
            return null;

        Facility facility = facilityFromLabel.get(label);
        if (facility == null) {
            throw new IllegalArgumentException("Invalid facility '" + label + "'");
        }
        return facility;
    }

    /**
     * Syslog facility numerical code
     */
    public int numericalCode() {
        return numericalCode;
    }

    /**
     * Syslog facility textual code. Not {@code null}.
     */
    public String label() {
        return label;
    }

    /**
     * Compare on {@link Facility#numericalCode()}
     */
    public static Comparator<Facility> comparator() {
        return new Comparator<Facility>() {
            @Override
            public int compare(Facility f1, Facility f2) {
                return Integer.compare(f1.numericalCode, f2.numericalCode);
            }
        };
    }
}