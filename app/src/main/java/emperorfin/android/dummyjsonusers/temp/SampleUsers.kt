package emperorfin.android.dummyjsonusers.temp


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


object SampleUsers {

    fun getUsers(): List<User> {

        val users = mutableListOf<User>()

        val hair = Hair(
            color = "Brown",
            type = "Curly"
        )

        val coordinatesHome = Coordinates(
            lat = -77.16213,
            lng = -92.084824,
        )

        val coordinatesCompany = Coordinates(
            lat = 71.814525,
            lng = -161.150263,
        )

        val addressHome = Address(
            address = "626 Main Street",
            city = "Phoenix",
            state = "Mississippi",
            stateCode = "MS",
            postalCode = "29112",
            coordinates = coordinatesHome,
            country = "United States",
        )

        val addressCompany = Address(
            address = "263 Tenth Street",
            city = "San Francisco",
            state = "Wisconsin",
            stateCode = "WI",
            postalCode = "37657",
            coordinates = coordinatesCompany,
            country = "United States",
        )

        val bank = Bank(
            cardExpire = "03/26",
            cardNumber = "9289760655481815",
            cardType = "Elo",
            currency = "CNY",
            iban = "YPUXISOBI7TTHPK2BR3HAIXL",
        )

        val company = Company(
            department = "Engineering",
            name = "Dooley, Kozey and Cronin",
            title = "Sales Manager",
            address = addressCompany
        )

        val crypto = Crypto(
            coin = "Bitcoin",
            wallet = "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a",
            network = "Ethereum (ERC20)",
        )

        var user = User(
            id = 1,
            firstName = "Emily",
            lastName = "Johnson",
            maidenName = "Smith",
            age = 28,
            gender = "female",
            email = "emily.johnson@x.dummyjson.com",
            phone = "+81 965-431-3024",
            username = "emilys",
            password = "emilyspass",
            birthDate = "1996-5-30",
            image = "https://dummyjson.com/icon/emilys/128",
            bloodGroup = "O-",
            height = 193.24,
            weight = 63.16,
            eyeColor = "Green",
            hair = hair,
            ip = "42.48.100.32",
            address = addressHome,
            macAddress = "47:fa:41:18:ec:eb",
            university = "University of Wisconsin--Madison",
            bank = bank,
            company = company,
            ein = "977-175",
            ssn = "900-590-289",
            userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36",
            crypto = crypto,
            role = "admin",
        )

        users.add(user)

        user = user.copy(
            id = 2,
            firstName = "Michael",
            lastName = "Williams",
            maidenName = "",
            age = 35,
            gender = "male",
            email = "michael.williams@x.dummyjson.com",
            phone = "+49 258-627-6644",
            username = "michaelw",
            password = "michaelwpass",
            birthDate = "1989-8-10",
            image = "https://dummyjson.com/icon/michaelw/128",
            bloodGroup = "B+",
            height = 186.22,
            weight = 76.32,
            eyeColor = "Red",
        )

        users.add(user)

        user = user.copy(
            id = 3,
            firstName = "Sophia",
            lastName = "Brown",
            maidenName = "",
            age = 42,
            gender = "female",
            email = "sophia.brown@x.dummyjson.com",
            phone = "+81 210-652-2785",
            username = "sophiab",
            password = "sophiabpass",
            birthDate = "1982-11-6",
            image = "https://dummyjson.com/icon/sophiab/128",
            bloodGroup = "O-",
            height = 177.72,
            weight = 52.6,
            eyeColor = "Hazel",
        )

        users.add(user)

        user = user.copy(
            id = 4,
            firstName = "James",
            lastName = "Davis",
            maidenName = "",
            age = 45,
            gender = "male",
            email = "james.davis@x.dummyjson.com",
            phone = "+49 614-958-9364",
            username = "jamesd",
            password = "jamesdpass",
            birthDate = "1979-5-4",
            image = "https://dummyjson.com/icon/jamesd/128",
            bloodGroup = "AB+",
            height = 193.31,
            weight = 62.1,
            eyeColor = "Amber",
        )

        users.add(user)

        user = user.copy(
            id = 5,
            firstName = "Emma",
            lastName = "Miller",
            maidenName = "Johnson",
            age = 30,
            gender = "female",
            email = "emma.miller@x.dummyjson.com",
            phone = "+91 759-776-1614",
            username = "emmaj",
            password = "emmajpass",
            birthDate = "1994-6-13",
            image = "https://dummyjson.com/icon/emmaj/128",
            bloodGroup = "AB-",
            height = 192.8,
            weight = 63.62,
            eyeColor = "Green",
        )

        users.add(user)

        user = user.copy(
            id = 6,
            firstName = "Olivia",
            lastName = "Wilson",
            maidenName = "",
            age = 22,
            gender = "female",
            email = "olivia.wilson@x.dummyjson.com",
            phone = "+91 607-295-6448",
            username = "oliviaw",
            password = "oliviawpass",
            birthDate = "2002-4-20",
            image = "https://dummyjson.com/icon/oliviaw/128",
            bloodGroup = "B+",
            height = 182.61,
            weight = 58.toDouble(),
            eyeColor = "Hazel",
        )

        users.add(user)

        return users

    }

}