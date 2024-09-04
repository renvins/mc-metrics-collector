-- changeset init:1
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username TEXT UNIQUE NOT NULL,
                                     password TEXT NOT NULL,
                                     enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS permissions (
                                           id SERIAL PRIMARY KEY,
                                           name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id INTEGER,
                                          role_id INTEGER,
                                          PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS role_permissions (
                                                role_id INTEGER,
                                                permission_id INTEGER,
                                                PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
    );
