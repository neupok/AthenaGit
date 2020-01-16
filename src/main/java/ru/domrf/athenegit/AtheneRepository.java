package ru.domrf.athenegit;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class AtheneRepository {
    private Repository repository;

    public AtheneRepository(Repository repository) {
        this.repository = repository;
    }

    public static AtheneRepository build(String repoDir) throws IOException {
        return new AtheneRepository(
                new FileRepositoryBuilder()
                .setGitDir(new File("my_repo/.git"))
                .build());
    }
}
