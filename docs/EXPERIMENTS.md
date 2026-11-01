# Learning experiments and interview evidence

No scores have been recorded. Compilation with zero executed tests is the initial build evidence. Show what you personally tested and learned here.

## Experiment template

```text
Date and title:
Question / hypothesis:
Code commit:
Model identifier and configuration (if applicable):
Prompt / policy / dataset version:
Setup and command:
Cases and expected behavior:
Observed behavior (include failures):
Metric, numerator, denominator, and repeats:
Latency and token usage (if measured):
Conclusion and limitations:
Next change:
Interview explanation in two sentences:
```

Use synthetic fixtures; never record secrets or real employee data.

## First experiments

| Experiment | Cases | Evidence |
| --- | --- | --- |
| Calendar matches policy | Weekday, weekend, synthetic holiday, reversed range, invalid date | Failing case, fix, executed regression tests |
| Local/MCP balance parity | John, Mary, Alex, unknown, spaces, blank | Actual invocation results and normalization differences |
| Extraction baseline | 20 labeled messages, including ambiguity | Expected/actual fields and per-field exact-match counts |

All three are **planned**. Add results and commit references when completed.

## Portfolio claim checks

- Describe features present in the referenced commit.
- Link evidence for quantitative claims.
- State dataset size, configuration, and limitations.
- Distinguish implemented, tested, and planned work.
- Show a failure and fix in plain language.
