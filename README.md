# Lekhai — Romanized Nepali Input

Lekhai is a full-stack app for typing Nepali in Romanized text and receiving a Devanagari result. The Next.js frontend sends input to a Spring Boot API, which processes it and returns JSON for display.

## File guide

### Frontend

- `frontend/app/page.tsx` — interactive page with input, API request, loading/error states, result display, and copy action.
- `frontend/app/layout.tsx` — shared Next.js document shell, metadata, and global stylesheet import.
- `frontend/app/globals.css` — colors, typography, responsive layout, form, and result-card styles.
- `frontend/package.json` — frontend scripts and Next.js/React dependencies.
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
cd frontend && npm run dev
```

The frontend calls `http://localhost:8080/api/transliterate` by default. Set `NEXT_PUBLIC_API_URL` to use a different backend URL.
