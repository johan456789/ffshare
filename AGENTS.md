# AGENTS.md

## Release versioning

This fork tracks upstream (`caydey/ffshare`) while shipping its own changes.
Release tags combine both, so the upstream base stays visible:

```
v{upstream_version}-{our_version}
```

- `upstream_version` mirrors the upstream `versionName` the fork is based on.
- `our_version` is a plain counter starting at `1`, bumped for every release
  cut from the same upstream base (e.g. `v2.0.0-1`, `v2.0.0-2`).
- When the fork rebases onto a new upstream version, the counter resets
  (e.g. upstream `v2.1.0` → our `v2.1.0-1`).

Rules:

- Tags must start with `v` — the release workflow (`.github/workflows/ci.yml`)
  only publishes on `v*` tags.
- Users update via Obtainium pointed at this repo's GitHub Releases. There is
  no in-app update checker, so tag ordering only matters to Obtainium.
- Known edge: `-N` is a semver prerelease identifier, so `v2.0.0-1` sorts
  *below* a plain upstream `v2.0.0`. Harmless while upstream is behind our
  base; if upstream ever ships the same base version, rebase and bump the
  counter rather than reusing the tag.

Cutting a release:

```bash
git tag v2.0.0-1 && git push origin v2.0.0-1
```
