package com.sujal.unitconverter;

public class FrequencyConverter {
    public enum Unit {
        Hertz,
        KiloHertz,
        MegaHertz,
        GigaHertz;

        // Helper method to convert text to one of the above constants
        public static FrequencyConverter.Unit fromString(String text) {
            if (text != null) {
                for (FrequencyConverter.Unit unit : FrequencyConverter.Unit.values()) {
                    if (text.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                }
            }

            throw new IllegalArgumentException("Cannot find a value for " + text);
        }
    }

    private final double multiplier;

    public FrequencyConverter(FrequencyConverter.Unit from, FrequencyConverter.Unit to) {
        double constant = 1;

        switch (from) {
            case Hertz:
                if (to == FrequencyConverter.Unit.KiloHertz) {
                    constant = 0.001;
                } else if (to == FrequencyConverter.Unit.MegaHertz) {
                    constant = 1e-6;
                } else if (to == FrequencyConverter.Unit.GigaHertz) {
                    constant = 1e-9;
                }
                break;
            case KiloHertz:
                if (to == FrequencyConverter.Unit.Hertz) {
                    constant = 1000;
                } else if (to == FrequencyConverter.Unit.MegaHertz) {
                    constant = 0.001;
                } else if (to == FrequencyConverter.Unit.GigaHertz) {
                    constant = 1e-6;
                }
                break;
            case MegaHertz:
                if (to == FrequencyConverter.Unit.Hertz) {
                    constant = 1e+6;
                } else if (to == FrequencyConverter.Unit.KiloHertz) {
                    constant = 1000;
                } else if (to == FrequencyConverter.Unit.GigaHertz) {
                    constant = 0.001;
                }
                break;
            case GigaHertz:
                if (to == FrequencyConverter.Unit.Hertz) {
                    constant = 1e+9;
                } else if (to == FrequencyConverter.Unit.KiloHertz) {
                    constant = 1e+6;
                } else if (to == FrequencyConverter.Unit.MegaHertz) {
                    constant = 1000;
                }
                break;
        }

        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
