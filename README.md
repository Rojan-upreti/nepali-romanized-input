# Lekhai — Romanized Nepali Input

Lekhai is a Spring Boot MVC app for typing Nepali in Romanized text and receiving a Devanagari result. Spring Boot renders the JSP page, and the page calls the same backend JSON endpoint for processing.

## File guide

### JSP frontend

- `backend/src/main/webapp/WEB-INF/jsp/index.jsp` — Spring-rendered single-page interface, including the input form and API call.
- `backend/src/main/resources/static/css/app.css` — styles for the JSP interface.

- `frontend/app/page.tsx` — optional Next.js version of the interface retained as a separate client frontend.
- `frontend/app/layout.tsx` — shared Next.js document shell, metadata, and global stylesheet import.
- `frontend/app/globals.css` — colors, typography, responsive layout, form, and result-card styles.
- `frontend/package.json` — optional Next.js frontend scripts and dependencies.
- `frontend/package-lock.json` — reproducible npm dependency versions.
- `frontend/next.config.mjs` — Next.js build configuration.
- `frontend/next-env.d.ts` — Next.js TypeScript declarations.
- `frontend/tsconfig.json` — strict TypeScript compiler settings.

### Backend

- `backend/src/main/java/com/lekhai/api/LekhaiApplication.java` — Spring Boot application entry point.
- `backend/src/main/java/com/lekhai/api/TransliterationController.java` — validates requests and exposes `POST /api/transliterate`.
- `backend/src/main/java/com/lekhai/api/TransliterationService.java` — converts common Romanized Nepali tokens to Devanagari.
- `backend/src/main/resources/application.properties` — sets the API port to `8080`.
- `backend/pom.xml` — Spring Boot, validation, Java, and Maven build configuration.

## Run locally

```bash
cd backend && mvn spring-boot:run
```

Open `http://localhost:8080` to use the JSP interface. The page calls `/api/transliterate` internally.
