package com.sujal.unitconverter;

public class TemperatureConverter {
    public enum Unit {
        Celsius,
        Fahrenheit,
        Kelvin,
        Rankine,
        Reaumur;

        // Helper method to convert text to one of the above constants
        public static TemperatureConverter.Unit fromString(String text) {
            if (text != null) {
                for (TemperatureConverter.Unit unit : TemperatureConverter.Unit.values()) {
                    if (text.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                }
            }

            throw new IllegalArgumentException("Cannot find a value for " + text);
        }
    }

    private final double multiplier;

    public TemperatureConverter(TemperatureConverter.Unit from, TemperatureConverter.Unit to) {
        double constant = 1;

        switch (from) {
            case Celsius:
                if (to == TemperatureConverter.Unit.Fahrenheit) {
                    constant = 33.8;
                } else if (to == TemperatureConverter.Unit.Kelvin) {
                    constant = 274.15;
                } else if (to == TemperatureConverter.Unit.Rankine) {
                    constant = 493.47;
                } else if (to == TemperatureConverter.Unit.Reaumur) {
                    constant = 0.8;
                }
                break;
            case Fahrenheit:
                if (to == TemperatureConverter.Unit.Celsius) {
                    constant = -17.22;
                } else if (to == TemperatureConverter.Unit.Kelvin) {
                    constant = 255.927778;
                } else if (to == TemperatureConverter.Unit.Rankine) {
                    constant = 460.67;
                } else if (to == TemperatureConverter.Unit.Reaumur) {
                    constant = -13.7777778;
                }
                break;
            case Kelvin:
                if (to == TemperatureConverter.Unit.Celsius) {
                    constant = -272.15;
                } else if (to == TemperatureConverter.Unit.Fahrenheit) {
                    constant = -457.87;
                } else if (to == TemperatureConverter.Unit.Rankine) {
                    constant = 1.8;
                } else if (to == TemperatureConverter.Unit.Reaumur) {
                    constant = -217.72;
                }
                break;
            case Rankine:
                if (to == TemperatureConverter.Unit.Celsius) {
                    constant = -272.594444;
                } else if (to == TemperatureConverter.Unit.Fahrenheit) {
                    constant = -458.67;
                } else if (to == TemperatureConverter.Unit.Kelvin) {
                    constant = 0.555555556;
                } else if (to == TemperatureConverter.Unit.Reaumur) {
                    constant = -218.075556;
                }
                break;
            case Reaumur:
                if (to == TemperatureConverter.Unit.Celsius) {
                    constant = 1.25;
                } else if (to == TemperatureConverter.Unit.Fahrenheit) {
                    constant = 34.25;
                } else if (to == TemperatureConverter.Unit.Kelvin) {
                    constant = 274.4;
                } else if (to == TemperatureConverter.Unit.Rankine) {
                    constant = 493.92;
                }
                break;
        }

        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
