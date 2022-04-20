# EUREX converter

A simple library which exchanges money from one currency into another, using the ECB reference exchange rate for a particular day (within the last 90 days). Example: convert 14.50 USD to CHF on July 8th

ECB reference rates as XML files [https://www.ecb.europa.eu/stats/exchange/eurofxref/html/index.en.html](https://www.ecb.europa.eu/stats/exchange/eurofxref/html/index.en.html)
90 days history [https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml](https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml)

## Prerequisites

1. Java 8 (or higher)
2. Maven (3.3.0 or higher)

### Building and Running

Building
```bash
$ mvn clean install package
```

Running 
```bash
$  java -jar target/EcbCurrencyConverter-jar-with-dependencies.jar --from=CHF --to=USD --onDate=2022-04-01 --amount=100
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.