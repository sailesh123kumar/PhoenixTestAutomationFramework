# Phoenix Test Automation Framework

A comprehensive Java-based End-to-End (E2E) Test Automation framework designed for testing **UI**, **API**, and **Mobile** applications.

## Overview

Phoenix is a robust, scalable, and maintainable test automation framework that provides a unified approach to testing across multiple application layers. It combines best practices in test automation with modern Java development patterns to ensure reliable, efficient, and maintainable tests.

## Features

- **Multi-Platform Support**
  - Web UI automation using Selenium WebDriver
  - REST API testing and validation
  - Mobile application testing

- **Robust Architecture**
  - Page Object Model (POM) for UI testing
  - Reusable utilities and helper functions
  - Centralized configuration management
  - Comprehensive logging and reporting

- **Test Reporting**
  - Detailed test execution reports
  - Screenshots and logs for failed tests
  - Cross-browser compatibility reports

- **Best Practices**
  - Fluent API design patterns
  - Data-driven testing support
  - Parameterized testing capabilities
  - Error handling and retry mechanisms

## Technology Stack

- **Language**: Java
- **Build Tool**: Maven/Gradle
- **Testing Framework**: TestNG/JUnit
- **UI Automation**: Selenium WebDriver
- **API Testing**: RestAssured/HttpClient
- **Mobile Testing**: Appium
- **Reporting**: Extent Reports/Allure
- **Version Control**: Git

## Project Structure

```
PhoenixTestAutomationFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/phoenix/ui/
│   │   │   │   ├── pages/          # Page Object Models
│   │   │   │   ├── locators/       # UI Element Locators
│   │   │   │   └── actions/        # UI Actions
│   │   │   ├── com/phoenix/api/
│   │   │   │   ├── endpoints/      # API Endpoints
│   │   │   │   ├── clients/        # HTTP Clients
│   │   │   │   └── models/         # Request/Response Models
│   │   │   ├── com/phoenix/mobile/
│   │   │   │   ├── screens/        # Mobile Screen Objects
│   │   │   │   └── actions/        # Mobile Actions
│   │   │   ├── com/phoenix/core/
│   │   │   │   ├── config/         # Configuration Management
│   │   │   │   ├── utils/          # Utility Classes
│   │   │   │   ├── listeners/      # Test Listeners
│   │   │   │   └── reporting/      # Reporting Utilities
│   │   │   └── com/phoenix/data/
│   │   │       └── testdata/       # Test Data
│   │   └── resources/
│   │       ├── config.properties    # Configuration Files
│   │       ├── testdata.json        # Test Data Files
│   │       └── log4j.properties     # Logging Config
│   └── test/
│       ├── java/
│       │   ├── com/phoenix/ui/tests/      # UI Test Cases
│       │   ├── com/phoenix/api/tests/     # API Test Cases
│       │   └── com/phoenix/mobile/tests/  # Mobile Test Cases
│       └── resources/
│           └── testng.xml           # TestNG Configuration
├── pom.xml                          # Maven Dependencies
└── README.md
```

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher (or Gradle equivalent)
- **Git**: Latest version

### Optional
- **Docker**: For containerized test execution
- **Jenkins**: For CI/CD integration
- **Allure**: For advanced reporting

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/sailesh123kumar/PhoenixTestAutomationFramework.git
   cd PhoenixTestAutomationFramework
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure environment**
   - Update `src/main/resources/config.properties` with your environment details
   - Set up WebDriver paths (ChromeDriver, GeckoDriver, etc.)
   - Configure API endpoints and mobile device details

## Configuration

### config.properties

```properties
# Browser Configuration
browser=chrome
headless=false
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# API Configuration
api.base.url=https://api.example.com
api.timeout=5000

# Mobile Configuration
app.path=/path/to/app.apk
device.name=emulator-5554
platform.version=12

# Logging
log.level=INFO
log.path=./logs
```

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test suite
```bash
mvn clean test -Dgroups=smoke
```

### Run with specific browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run API tests only
```bash
mvn clean test -Dtest=**/api/tests/**
```

### Run Mobile tests only
```bash
mvn clean test -Dtest=**/mobile/tests/**
```

### Generate Reports
```bash
mvn allure:serve
```

## Test Examples

### UI Test Example
```java
@Test
public void verifyLoginFunctionality() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.navigateToLoginPage()
             .enterUsername("testuser")
             .enterPassword("password123")
             .clickLoginButton();
    
    DashboardPage dashboardPage = new DashboardPage(driver);
    Assert.assertTrue(dashboardPage.isUserLoggedIn());
}
```

### API Test Example
```java
@Test
public void verifyGetUserEndpoint() {
    ApiClient apiClient = new ApiClient();
    Response response = apiClient.getUser(userId);
    
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertNotNull(response.getBody().getId());
}
```

## Best Practices

1. **Page Object Model**: Keep page-related code in dedicated page classes
2. **Data-Driven Testing**: Use external data sources for test inputs
3. **Explicit Waits**: Avoid Thread.sleep(); use WebDriverWait instead
4. **Error Handling**: Implement proper exception handling and logging
5. **Test Independence**: Ensure tests don't depend on execution order
6. **Meaningful Assertions**: Use descriptive assertion messages
7. **Code Reusability**: Create utility methods for common operations

## Continuous Integration

This framework is designed to integrate with CI/CD pipelines:

- **Jenkins**: Use provided Jenkinsfile for pipeline configuration
- **GitHub Actions**: Automated test execution on push/PR
- **Docker**: Run tests in containerized environments

## Reporting

Test reports are generated in multiple formats:

- **TestNG Reports**: `target/surefire-reports/`
- **Allure Reports**: Available via `mvn allure:serve`
- **Custom HTML Reports**: Located in `target/reports/`

## Troubleshooting

### Common Issues

**WebDriver not found**
- Ensure WebDriver executables are in system PATH or configure driver path in config.properties

**Connection timeout errors**
- Verify API endpoint URLs and network connectivity
- Check firewall and proxy settings

**Element not found exceptions**
- Verify element locators are correct
- Add explicit waits for dynamic elements
- Check for dynamic ID/class changes

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Support

For issues, questions, or suggestions:

- Open an [Issue](https://github.com/sailesh123kumar/PhoenixTestAutomationFramework/issues)
- Contact: sailesh123kumar@example.com

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Changelog

### Version 1.0.0
- Initial framework release
- UI automation support
- API testing capabilities
- Mobile testing integration

---

**Happy Testing! 🚀**
