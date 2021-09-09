rootProject.name = "cloud-native"

files("discovery").forEach { dir ->
    dir.listFiles()?.forEach {
        if (it.isDirectory) {
            val module = it.name
            include(module)
            project(":$module").projectDir = it
        }
    }
}