import { render } from '@testing-library/react';

import KakeiboUi from './kakeibo-ui';

describe('KakeiboUi', () => {
  it('should render successfully', () => {
    const { baseElement } = render(<KakeiboUi />);
    expect(baseElement).toBeTruthy();
  });
});
