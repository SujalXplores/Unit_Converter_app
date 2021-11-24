package com.sujal.unitconverter;

public class SpeedConverter {
    public enum Unit {
        MeterPerSecond,
        FootPerSecond,
        Knot,
        KilometerPerHour,
        MilesPerHour;

        // Helper method to convert text to one of the above constants
        public static SpeedConverter.Unit fromString(String text) {
            if (text != null) {
                for (SpeedConverter.Unit unit : SpeedConverter.Unit.values()) {
                    if (text.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                }
            }

            throw new IllegalArgumentException("Cannot find a value for " + text);
        }
    }

    private final double multiplier;

    public SpeedConverter(SpeedConverter.Unit from, SpeedConverter.Unit to) {
        double constant = 1;

        switch (from) {
            case MeterPerSecond:
                if (to == SpeedConverter.Unit.FootPerSecond) {
                    constant = 3.28084;
                } else if (to == SpeedConverter.Unit.Knot) {
                    constant = 1.94384;
                } else if (to == SpeedConverter.Unit.KilometerPerHour) {
                    constant = 3.6;
                } else if (to == SpeedConverter.Unit.MilesPerHour) {
                    constant = 2.23694;
                }
                break;
            case FootPerSecond:
                if (to == Unit.MeterPerSecond) {
                    constant = 0.3048;
                } else if (to == SpeedConverter.Unit.Knot) {
                    constant = 0.592484;
                } else if (to == SpeedConverter.Unit.KilometerPerHour) {
                    constant = 1.09728;
                } else if (to == SpeedConverter.Unit.MilesPerHour) {
                    constant = 0.681818;
                }
                break;
            case Knot:
                if (to == Unit.MeterPerSecond) {
                    constant = 0.514444;
                } else if (to == SpeedConverter.Unit.FootPerSecond) {
                    constant = 1.68781;
                } else if (to == SpeedConverter.Unit.KilometerPerHour) {
                    constant = 1.852;
                } else if (to == SpeedConverter.Unit.MilesPerHour) {
                    constant = 1.15078;
                }
                break;
            case KilometerPerHour:
                if (to == Unit.MeterPerSecond) {
                    constant = 0.277778;
                } else if (to == SpeedConverter.Unit.FootPerSecond) {
                    constant = 0.911344;
                } else if (to == SpeedConverter.Unit.Knot) {
                    constant = 0.539957;
                } else if (to == SpeedConverter.Unit.MilesPerHour) {
                    constant = 0.621371;
                }
                break;
            case MilesPerHour:
                if (to == Unit.MeterPerSecond) {
                    constant = 0.44704;
                } else if (to == SpeedConverter.Unit.FootPerSecond) {
                    constant = 1.46667;
                } else if (to == SpeedConverter.Unit.Knot) {
                    constant = 0.868976;
                } else if (to == SpeedConverter.Unit.KilometerPerHour) {
                    constant = 1.60934;
                }
                break;
        }

        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
