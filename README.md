# Automation Project

## Testng Lifecycle
```
@BeforeSuite
    @BeforeTest
        @BeforeClass
            @BeforeGroups
                @BeforeMethod
                    @Test
                @AfterMethod
            @AfterGroups
        @AfterClass
    @AfterTest
@AfterSuite
```