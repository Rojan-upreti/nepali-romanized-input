// App shell: sets global metadata, language, and shared styles for every page.
import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Lekhai — Nepali Romanized Input",
  description: "Write Nepali naturally in Romanized text and see it in Devanagari.",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
