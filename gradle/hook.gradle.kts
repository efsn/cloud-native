tasks {
    register<Copy>("copyGitHooks") {
        val old = rootProject.file(".git/hooks/pre-commit")
        if (!old.exists()) {
            from(rootProject.file("gradle/script/pre-commit"))
            into(rootProject.file(".git/hooks"))
            fileMode(0755)
//            Runtime.getRuntime().exec("chmod -R +x .git/hooks")
        }
    }

    named("ktlint") { dependsOn("copyGitHooks") }
}
